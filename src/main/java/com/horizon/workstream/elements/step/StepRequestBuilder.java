package com.horizon.workstream.elements.step;

import com.horizon.workstream.elements.workflow.WorkFlowContext;
import com.horizon.workstream.elements.workflow.information.InformationStream;

public interface StepRequestBuilder<T, W extends WorkFlowContext> {

  T build(final W workFlowContext,
      final InformationStream data);
}
