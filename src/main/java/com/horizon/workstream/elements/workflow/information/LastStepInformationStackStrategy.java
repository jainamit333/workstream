package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.StepResponse;
import java.util.Map;

// TODO: 27/03/20 check if have to be converted to ? extends StepResponse.
public class LastStepInformationStackStrategy<T extends StepResponse>
    implements InformationStackStrategy<T> {

  private final Level level;

  public LastStepInformationStackStrategy(final Level level) {
    this.level = level;
  }

  @Override
  public Map<? extends Class<StepResponse>, ? extends StepResponse> updateInformation(
      final Map<? extends Class<StepResponse>, ? extends StepResponse> currentInformation,
      final T stepResponse) {
    return null;
  }

  @Override
  public Level getLevel() {
    return level;
  }
}
