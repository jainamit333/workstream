package com.horizon.workstream.elements.execution;

import com.google.common.base.Verify;
import com.horizon.workstream.elements.step.IStep;
import com.horizon.workstream.elements.step.StepRequestBuilder;
import com.horizon.workstream.elements.step.StepResponse;
import com.horizon.workstream.elements.workflow.WorkFlowContext;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 * Individual node of the {@link ExecutionGraph}. Which will contain single
 * {@link com.horizon.workstream.elements.step.IStep} which have to be executed by the workflow.
 */
public class ExecutionNode<I, O extends StepResponse, R extends StepRequestBuilder<I, ?
    extends WorkFlowContext>,
    T extends IStep<I, O, R>> {

  private final static int DEFAULT_RETRY_COUNT = 3;

  private final String id;

  private final Class<T> steClazz;

  private final Set<ExecutionNode> childNodes;

  // TODO: 28/03/20 retry and skip to be added.
  private Predicate<? extends Exception> retryOnException;

  private Predicate<O> retryBasedOnStepResult;

  private Predicate<I> skipBasedOnInput;

  private int retryCount;

  public ExecutionNode(final String id, final Class<T> stepClazz) {
    this.id = id;
    steClazz = stepClazz;
    childNodes = new HashSet<>();
    retryCount = DEFAULT_RETRY_COUNT;
  }

  // TODO: 28/03/20 how to make sure it is called only by the Graph.
  void addChildNode(final ExecutionNode child) {
    childNodes.add(child);
  }

  public static class ExecutionNodeBuilder<I, O extends StepResponse,
      R extends StepRequestBuilder<I, ? extends WorkFlowContext>,
      T extends IStep<I, O, R>> {

    private final String id;
    private final Class<T> stepCazz;
    private Integer retryCount;

    public ExecutionNodeBuilder(final String id, final Class<T> stepClazz) {

      Verify.verifyNotNull(stepClazz, "Step Clazz cannot be null");
      this.id = StringUtils.isEmpty(id) ? UUID.randomUUID().toString() : id;
      stepCazz = stepClazz;
    }

    public ExecutionNodeBuilder<I, O, R, T> retry(final int retryCount) {
      this.retryCount = retryCount;
      return this;
    }

    public ExecutionNode<I, O, R, T> build() {

      final ExecutionNode<I, O, R, T> executionNode = new ExecutionNode<>(id, stepCazz);
      if (retryCount != null)
        executionNode.retryCount = retryCount;
      return executionNode;
    }
  }
}
