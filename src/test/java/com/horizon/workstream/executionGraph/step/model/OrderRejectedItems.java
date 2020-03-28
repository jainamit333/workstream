package com.horizon.workstream.executionGraph.step.model;

import com.horizon.workstream.elements.step.StepResponse;
import java.util.List;

public class OrderRejectedItems implements StepResponse {

  private List<LineItem> unavailableLineItems;
}
