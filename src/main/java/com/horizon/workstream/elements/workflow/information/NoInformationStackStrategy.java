package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.StepResponse;
import java.util.Collections;
import java.util.Map;

public class NoInformationStackStrategy<T extends StepResponse>
    implements InformationStackStrategy<T> {

  @Override
  public Map<? extends Class<StepResponse>, ? extends StepResponse> updateInformation(
      final Map<? extends Class<StepResponse>, ? extends StepResponse> currentInformation,
      final T stepResponse) {
    return Collections.emptyMap();
  }

  @Override
  public Level getLevel() {
    return Level.LINIENT;
  }
}
