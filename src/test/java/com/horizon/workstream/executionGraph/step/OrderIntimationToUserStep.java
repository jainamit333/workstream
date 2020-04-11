
package com.horizon.workstream.executionGraph.step;

import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.elements.step.VoidResponse;
import com.horizon.workstream.executionGraph.step.stepBuilder.OrderProcessVoidStepRequestBuilder;
import java.util.Optional;

public class OrderIntimationToUserStep implements
    IStep<Void, VoidResponse, OrderProcessVoidStepRequestBuilder> {
  @Override
  public String name() {
    return null;
  }

  @Override
  public void onError() {

  }

  @Override
  public void onSuccess() {

  }

  @Override
  public Optional<VoidResponse> execute(final Void input) {
    return Optional.empty();
  }

  @Override
  public OrderProcessVoidStepRequestBuilder getRequestBuilder() {
    return null;
  }
}

