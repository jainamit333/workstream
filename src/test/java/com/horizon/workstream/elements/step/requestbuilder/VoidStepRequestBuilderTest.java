package com.horizon.workstream.elements.step.requestbuilder;

import com.horizon.workstream.elements.workflow.information.InformationStream;
import com.horizon.workstream.executionGraph.OrderProcessingWorkflowContext;
import org.junit.Test;



public class VoidStepRequestBuilderTest {

  @Test
  public void temp() {

    final VoidStepRequestBuilder<OrderProcessingWorkflowContext> builder =
        new VoidStepRequestBuilder<OrderProcessingWorkflowContext>() {
          @Override
          public Void build(final OrderProcessingWorkflowContext workFlowContext,
              final InformationStream input) {
            return super.build(workFlowContext, input);
          }
        };
  }

}
