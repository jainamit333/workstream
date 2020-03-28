package com.horizon.workstream.executionGraph.step;

import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.executionGraph.step.model.OrderInventorConfirmation;
import com.horizon.workstream.executionGraph.step.model.OrderRejectedItems;
import com.horizon.workstream.executionGraph.step.stepBuilder.EvaluateNonAvailableItemStepReqBuilder;
import java.util.Optional;
import java.util.logging.Logger;

public class EvaluateNonAvailableItemStep implements
    IStep<OrderInventorConfirmation, OrderRejectedItems, EvaluateNonAvailableItemStepReqBuilder> {

  private static final String NAME = "Evaluate Available Items";
  private static final Logger LOGGER = Logger.getLogger(EvaluateNonAvailableItemStep.NAME);

  @Override
  public String name() {
    return NAME;
  }

  @Override
  public void onError() {

    LOGGER.info("On Error case of the EvaluateNonAvailableItemStep");
  }

  @Override
  public void onSuccess() {

  }

  @Override
  public Optional<OrderRejectedItems> execute(final OrderInventorConfirmation input) {
    return Optional.empty();
  }

  @Override
  public EvaluateNonAvailableItemStepReqBuilder getRequestBuilder() {
    return null;
  }
}
