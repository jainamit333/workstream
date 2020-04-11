package com.horizon.workstream.executionGraph;

import static com.google.common.collect.Sets.newHashSet;
import static com.horizon.workstream.elements.execution.ExecutionNode.ExecutionNodeBuilder.BUILD;

import com.horizon.workstream.elements.execution.ExecutionGraph;
import com.horizon.workstream.elements.execution.ExecutionGraph.ExecutionGraphBuilder;
import com.horizon.workstream.executionGraph.step.BillGenerationStep;
import com.horizon.workstream.executionGraph.step.DiscountEvaluationStep;
import com.horizon.workstream.executionGraph.step.EvaluateNonAvailableItemStep;
import com.horizon.workstream.executionGraph.step.NotificationSenderStep;
import com.horizon.workstream.executionGraph.step.OrderIntimationToUserStep;
import com.horizon.workstream.executionGraph.step.OrderIntimationToWarehouseStep;
import com.horizon.workstream.executionGraph.step.PreDiscountEvaluationStep;
import com.horizon.workstream.executionGraph.step.PreTaxEvaluationStep;
import com.horizon.workstream.executionGraph.step.PreUserDiscountEvaluationStep;
import com.horizon.workstream.executionGraph.step.ShippingEvaluationStep;
import com.horizon.workstream.executionGraph.step.TaxEvaluationStep;
import com.horizon.workstream.executionGraph.step.UserDiscountEvaluationStep;
import java.util.Collections;
import org.junit.Test;

/**
 * Check the ExecutionGrExample.txt to visualise the graph which we will try to create.
 */
public class ExecutionGraphBuilderExamples {

  @Test
  public void buildWorkflowExecutionFlowExample() {

    final ExecutionGraph main = ExecutionGraphBuilder
        .BUILD("Order Processing ", BUILD("1", OrderIntimationToUserStep.class))
        .then(BUILD("2", EvaluateNonAvailableItemStep.class))
        .fork(
            BUILD("3", PreTaxEvaluationStep.class),
            BUILD("5", PreDiscountEvaluationStep.class),
            BUILD("7", PreUserDiscountEvaluationStep.class),
            BUILD("9", NotificationSenderStep.class))
        .then(BUILD("4", TaxEvaluationStep.class), Collections.singleton("3"))
        .then(BUILD("6", DiscountEvaluationStep.class), Collections.singleton("5"))
        .then(BUILD("8", UserDiscountEvaluationStep.class), Collections.singleton("7"))
        .join(BUILD("10", ShippingEvaluationStep.class), newHashSet("6", "8"))
        .join(BUILD("11", BillGenerationStep.class), newHashSet("4", "10"))
        .fork("11",
            BUILD("12", OrderIntimationToUserStep.class),
            BUILD("13", OrderIntimationToWarehouseStep.class))
        .build();
  }
}
