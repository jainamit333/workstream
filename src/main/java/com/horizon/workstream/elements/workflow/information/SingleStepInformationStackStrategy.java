package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.StepResponse;
import java.util.Map;

public class SingleStepInformationStackStrategy<T extends StepResponse>
    implements InformationStackStrategy<T> {

  private final Level level;

  public SingleStepInformationStackStrategy(
      final Level level) {
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
