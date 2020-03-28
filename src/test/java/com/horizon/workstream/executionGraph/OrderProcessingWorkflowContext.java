package com.horizon.workstream.executionGraph;

import com.horizon.workstream.elements.workflow.WorkFlowContext;
import com.horizon.workstream.executionGraph.step.model.LineItem;
import java.util.List;

public class OrderProcessingWorkflowContext implements WorkFlowContext {

  private String userId;
  private String orderId;
  private List<LineItem> requestItems;

}
