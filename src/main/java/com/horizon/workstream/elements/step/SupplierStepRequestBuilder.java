package com.horizon.workstream.elements.step;

import com.horizon.workstream.elements.workflow.WorkFlowContext;
import com.horizon.workstream.elements.workflow.information.InformationStream;

public abstract class SupplierStepRequestBuilder<W extends WorkFlowContext>
    implements StepRequestBuilder<Void, W> {

  public SupplierStepRequestBuilder() {
  }

  public Void build(final W workFlowContext,
      final InformationStream input) {
    return null;
  }
}
