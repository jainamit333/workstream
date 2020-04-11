package com.horizon.workstream.elements.workflow.information;

import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.elements.step.StepResponse;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InformationStream {

  private Map<? extends Class<StepResponse>, Map<? extends Class<IStep>, ResponseWrapper>> data;

  public Optional<StepResponse> getFromParticularStep(final Class<? extends StepResponse> type,
      final Class<? extends IStep> fromClass) {

    return getStepResponses(type, responseWrapper -> responseWrapper.stepIdentifier == fromClass)
        .max(Comparator.comparing(StepResponse::createdAt));
  }

  public Optional<StepResponse> getOldestFromParticularStep(
      final Class<? extends StepResponse> type,
      final Class<? extends IStep> fromClass) {
    return getStepResponses(type, responseWrapper -> responseWrapper.stepIdentifier == fromClass)
        .min(Comparator.comparing(StepResponse::createdAt));
  }

  public <T extends StepResponse> Optional<T> getLatest(final Class<T> type) {
    return (Optional<T>) getStepResponses(type, o -> true)
        .max(Comparator.comparing(StepResponse::createdAt));
  }

  public Optional<StepResponse> getOldest(final Class<? extends StepResponse> type) {
    return getStepResponses(type, o -> true).min(Comparator.comparing(StepResponse::createdAt));
  }

  public <T extends StepResponse> Optional<T> get(final Class<T> type) {
    return getLatest(type);
  }

  private Stream<StepResponse> getStepResponses(final Class<? extends StepResponse> type,
      final Predicate<ResponseWrapper> stepMatch) {
    final Map<? extends Class<IStep>, ResponseWrapper> map = data.get(type);
    return map.values().stream().filter(stepMatch).map(
        responseWrapper -> responseWrapper.stepResponse).flatMap(
        (Function<List<StepResponse>, Stream<StepResponse>>) Collection::stream);
  }

  // TODO: 04/04/20 Check if generics have to be improved
  public class ResponseWrapper {

    private Class<? extends IStep> stepIdentifier;
    private List<StepResponse> stepResponse;

  }
}
