package com.horizon.workstream.executionGraph.step.stepBuilder;

import com.horizon.workstream.elements.step.StepRequestBuilder;
import com.horizon.workstream.elements.workflow.information.InformationStream;
import com.horizon.workstream.executionGraph.OrderProcessingWorkflowContext;
import com.horizon.workstream.executionGraph.step.model.OrderInventorConfirmation;

public class EvaluateNonAvailableItemStepReqBuilder
    implements StepRequestBuilder<OrderInventorConfirmation, OrderProcessingWorkflowContext> {

  @Override
  public OrderInventorConfirmation build(final OrderProcessingWorkflowContext workFlowContext,
      final InformationStream data) {
    return null;
  }
}
