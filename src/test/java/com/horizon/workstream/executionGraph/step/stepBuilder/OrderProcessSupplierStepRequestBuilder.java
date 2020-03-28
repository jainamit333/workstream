package com.horizon.workstream.executionGraph.step.stepBuilder;

import com.horizon.workstream.elements.step.SupplierStepRequestBuilder;
import com.horizon.workstream.executionGraph.OrderProcessingWorkflowContext;

public class OrderProcessSupplierStepRequestBuilder
    extends SupplierStepRequestBuilder<OrderProcessingWorkflowContext> {

  public static OrderProcessSupplierStepRequestBuilder INSTANCE =
      new OrderProcessSupplierStepRequestBuilder();

  private OrderProcessSupplierStepRequestBuilder() {
  }
}
