package com.horizon.workstream.executionGraph.step.stepBuilder;

import com.horizon.workstream.elements.step.requestbuilder.VoidStepRequestBuilder;
import com.horizon.workstream.executionGraph.OrderProcessingWorkflowContext;

// TODO: 04/04/20 remove this , it is not required, can have simple inititlation of
//  SupplierStepRequestBuilder
public class OrderProcessVoidStepRequestBuilder
    extends VoidStepRequestBuilder<OrderProcessingWorkflowContext> {

  public static OrderProcessVoidStepRequestBuilder INSTANCE =
      new OrderProcessVoidStepRequestBuilder();

  private OrderProcessVoidStepRequestBuilder() {
  }
}
