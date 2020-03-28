package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.elements.step.StepResponse;
import java.util.List;
import java.util.Map;

public class InformationStream {

  private Map<? extends Class<StepResponse>, Map<? extends Class<IStep>, ResponseWrapper>> data;

  public StepResponse getLatest(final Class<? extends IStep> fromClass) {
    return null;
  }

  public StepResponse getOldest(final Class<? extends IStep> fromClass) {
    return null;
  }

  public class ResponseWrapper {

    private Class<? extends IStep> stepIdentifier;
    private List<? extends StepResponse> stepResponse;

  }
}
