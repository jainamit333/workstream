package com.horizon.workstream.elements.step;

import com.horizon.workstream.elements.workflow.WorkFlowContext;
import java.util.Optional;

public interface IStep<I, O extends StepResponse, R extends StepRequestBuilder<I, ?
    extends WorkFlowContext>> {

  String name();

  void onError();

  void onSuccess();

  Optional<O> execute(I input);

  R getRequestBuilder();
}
