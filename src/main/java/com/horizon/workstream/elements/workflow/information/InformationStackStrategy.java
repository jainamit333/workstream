package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.StepResponse;
import java.util.Map;

/**
 * During the creation of the workflow this strategy have to be passed to it. It will define how
 * the responses of each step will be saved in the Map which is flowing in the workflow.
 */
public interface InformationStackStrategy<T extends StepResponse> {

  Map<? extends Class<StepResponse>, ? extends StepResponse> updateInformation(
      final Map<? extends Class<StepResponse>, ? extends StepResponse> currentInformation,
      final T stepResponse);

  Level getLevel();

  public static enum Level {
    STRICT, LINIENT
  }
}
