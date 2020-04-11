package com.horizon.workstream.elements.step.requestbuilder;

import com.horizon.workstream.elements.step.StepRequestBuilder;
import com.horizon.workstream.elements.workflow.WorkFlowContext;
import com.horizon.workstream.elements.workflow.information.InformationStream;

public abstract class VoidStepRequestBuilder<W extends WorkFlowContext>
    implements StepRequestBuilder<Void, W> {

  public Void build(final W workFlowContext,
      final InformationStream input) {
    return null;
  }
}
