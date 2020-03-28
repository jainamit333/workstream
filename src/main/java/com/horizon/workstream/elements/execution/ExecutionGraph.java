package com.horizon.workstream.elements.execution;

import com.google.common.base.Verify;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * Execution Graph represent the execution flow which will be feed to the Workflow manager for
 * execution. Each execution should have exactly one start point.
 * Graph should a a-cyclic directional graph.
 * Each execution graph can have multi end notes.
 */
public class ExecutionGraph {

  private final String name;

  private final ExecutionNode root;

  private final Set<ExecutionNode> endNodes;

  public ExecutionGraph(final String name, final ExecutionNode root,
      final Set<ExecutionNode> endNodes) {
    this.name = name;
    this.root = root;
    this.endNodes = endNodes;
  }

  public String getName() {
    return name;
  }

  public ExecutionNode getRoot() {
    return root;
  }

  public Set<ExecutionNode> getEndNodes() {

    return Collections.unmodifiableSet(endNodes);
  }

  public static class ExecutionBuilder {

    private final String name;
    private final ExecutionNode root;
    private final Set<ExecutionNode> endNodes;
    private ExecutionNode currentNode;

    public ExecutionBuilder(final String name, final ExecutionNode root) {

      Verify.verify(StringUtils.isNoneEmpty(name), "id cannot be null or empty");
      Verify.verifyNotNull(root, "Root node cannot be null");

      this.name = name;
      this.root = root;
      currentNode = this.root;
      endNodes = new HashSet<>();
    }

    public ExecutionBuilder then(final ExecutionNode node) {

      Verify.verifyNotNull(node, "Next step should not be null");
      currentNode.addChildNode(node);
      currentNode = node;
      endNodes.remove(currentNode);
      endNodes.add(node);
      return this;
    }

    /**
     * If any of the execution Graph is null, it will be filtered out.
     * @param executionGraphs
     * @return
     */
    public ExecutionBuilder fork(final ExecutionGraph... executionGraphs) {

      Arrays.stream(executionGraphs).filter(Objects::nonNull).forEach(
          executionGraph -> {
            currentNode.addChildNode(executionGraph.getRoot());
            endNodes.remove(currentNode);
            endNodes.addAll(executionGraph.getEndNodes());
          });
      return this;
    }

    public ExecutionBuilder join(final ExecutionNode executionNode) {

      Verify.verifyNotNull(executionNode, "New Node cannot be null");
      endNodes.forEach(executionNode1 -> executionNode1.addChildNode(executionNode1));
      endNodes.clear();
      endNodes.add(executionNode);
      return this;
    }

    public ExecutionBuilder join(final ExecutionGraph executionGraph) {

      Verify.verifyNotNull(executionGraph, "New Graph cannot be null");
      endNodes.forEach(executionNode -> executionNode.addChildNode(executionGraph.getRoot()));
      endNodes.clear();
      endNodes.addAll(executionGraph.getEndNodes());
      return this;
    }

    public ExecutionGraph build() {

      validate();
      return new ExecutionGraph(name, root, endNodes);
    }

    /**
     * Should by non-cyclic graph
     */
    private void validate() {

    }

  }

}
