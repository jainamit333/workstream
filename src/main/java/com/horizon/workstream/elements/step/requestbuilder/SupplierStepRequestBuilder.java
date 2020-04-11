
package com.horizon.workstream.elements.step.requestbuilder;

import com.horizon.workstream.elements.step.StepRequestBuilder;
import com.horizon.workstream.elements.step.StepResponse;
import com.horizon.workstream.elements.step.exception.NoRequestFoundException;
import com.horizon.workstream.elements.workflow.WorkFlowContext;
import com.horizon.workstream.elements.workflow.information.InformationStream;
import java.util.Optional;

/**
 * When have to feed the response of one step as request of the other step.
 * @param <T>
 * @param <W>
 */
public abstract class SupplierStepRequestBuilder<T extends StepResponse, W extends WorkFlowContext>
    implements StepRequestBuilder<T, W> {

  private final Class<T> clazz;

  public SupplierStepRequestBuilder(final Class<T> clazz) {
    this.clazz = clazz;
  }

  public T build(final W workFlowContext, final InformationStream input) {

    final Optional<StepResponse> stepResponse = input.get(clazz);
    return (T) stepResponse.orElseGet(() -> { throw new NoRequestFoundException(); });

  }
}
