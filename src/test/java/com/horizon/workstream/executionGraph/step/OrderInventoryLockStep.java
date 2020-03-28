package com.horizon.workstream.executionGraph.step;

import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.executionGraph.step.model.OrderInventorConfirmation;
import com.horizon.workstream.executionGraph.step.stepBuilder.OrderProcessSupplierStepRequestBuilder;
import java.util.Optional;
import java.util.logging.Logger;

public class OrderInventoryLockStep implements
    IStep<Void, OrderInventorConfirmation, OrderProcessSupplierStepRequestBuilder> {

  private static final String NAME = "Order-Inventor-Confirmation";
  private static final Logger LOGGER = Logger.getLogger(OrderInventoryLockStep.NAME);

  @Override
  public String name() {
    return NAME;
  }

  @Override
  public void onError() {
    LOGGER.info("Release the items from the inventor which have been locked");
  }

  @Override
  public void onSuccess() {
    LOGGER.info("Inventory have been successfully updated");
  }

  @Override
  public Optional<OrderInventorConfirmation> execute(final Void input) {
    return Optional.empty();
  }

  @Override
  public OrderProcessSupplierStepRequestBuilder getRequestBuilder() {
    return OrderProcessSupplierStepRequestBuilder.INSTANCE;
  }
}
