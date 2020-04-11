package com.horizon.workstream.elements.execution;

import com.google.common.base.Verify;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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

  public void printExecution() {
  }

  public static class ExecutionGraphBuilder {

    private final String name;
    private final ExecutionNode root;
    private final Map<String, ExecutionNode> endNodes;

    private ExecutionGraphBuilder(final String name, final ExecutionNode root) {

      Verify.verify(StringUtils.isNoneEmpty(name), "Name cannot be null or empty");
      Verify.verifyNotNull(root, "Root node cannot be null");

      this.name = name;
      this.root = root;
      endNodes = new HashMap<>();
      endNodes.put(root.getId(), root);
    }

    public static ExecutionGraphBuilder BUILD(final String name,
        final ExecutionNode.ExecutionNodeBuilder root) {
      return new ExecutionGraphBuilder(name, root.build());
    }

    public ExecutionGraphBuilder then(final ExecutionNode.ExecutionNodeBuilder nodeBuilder) {

      final ExecutionNode currentEndNode = getSingleEndNode();
      final ExecutionNode node = nodeBuilder.build();
      currentEndNode.addChildNode(node);
      endNodes.clear();
      endNodes.put(node.getId(), node);
      return this;
    }

    public ExecutionGraphBuilder then(final ExecutionGraphBuilder executionGraphBuilder) {

      final ExecutionNode currentEndNode = getSingleEndNode();
      final ExecutionNode node = executionGraphBuilder.root;
      currentEndNode.addChildNode(node);
      endNodes.clear();
      endNodes.putAll(executionGraphBuilder.endNodes);
      return this;
    }

    public ExecutionGraphBuilder then(final ExecutionNode.ExecutionNodeBuilder nodeBuilder,
        final Set<String> endNodeIds) {

      final ExecutionNode node = nodeBuilder.build();
      endNodeIds.forEach(endNodeId -> {
        final ExecutionNode currentEndNode = endNodes.get(endNodeId);
        currentEndNode.addChildNode(node);
        endNodes.remove(endNodeId);
      });
      endNodes.put(node.getId(), node);
      return this;
    }

    public ExecutionGraphBuilder fork(final ExecutionNode.ExecutionNodeBuilder... nodeBuilders) {
      final ExecutionNode currentEndNode = getSingleEndNode();
      Arrays.stream(nodeBuilders).map(nodeBuilder -> nodeBuilder.build())
          .forEach(executionNode -> {
            currentEndNode.addChildNode(executionNode);
            endNodes.put(executionNode.getId(), executionNode);
          });
      endNodes.remove(currentEndNode.getId());
      return this;
    }

    public ExecutionGraphBuilder fork(final ExecutionGraphBuilder... executionGraphBuilder) {

      final ExecutionNode currentEndNode = getSingleEndNode();

      Arrays.stream(executionGraphBuilder).forEach(executionGraphBuilder1 -> {
        currentEndNode.addChildNode(executionGraphBuilder1.root);
        endNodes.putAll(executionGraphBuilder1.endNodes);
      });
      endNodes.remove(currentEndNode.getId());
      return this;
    }

    public ExecutionGraphBuilder fork(final String endNodeId,
        final ExecutionNode.ExecutionNodeBuilder... nodeBuilders) {
      final ExecutionNode currentEndNode = endNodes.get(endNodeId);
      Arrays.stream(nodeBuilders).map(nodeBuilder -> nodeBuilder.build())
          .forEach(executionNode -> {
            currentEndNode.addChildNode(executionNode);
            endNodes.put(executionNode.getId(), executionNode);
          });
      endNodes.remove(currentEndNode.getId());
      return this;
    }

    public ExecutionGraphBuilder fork(final String endNodeId,
        final ExecutionGraphBuilder... executionGraphBuilder) {

      final ExecutionNode currentEndNode = endNodes.get(endNodeId);

      Arrays.stream(executionGraphBuilder).forEach(executionGraphBuilder1 -> {
        currentEndNode.addChildNode(executionGraphBuilder1.root);
        endNodes.putAll(executionGraphBuilder1.endNodes);
      });
      endNodes.remove(currentEndNode.getId());
      return this;
    }

    public ExecutionGraphBuilder join(final ExecutionNode.ExecutionNodeBuilder nodeBuilder) {
      Verify.verify(endNodes.size() > 1, "Only single end node present");
      final ExecutionNode node = nodeBuilder.build();
      endNodes.values().forEach(executionNode -> executionNode.addChildNode(node));
      endNodes.clear();
      endNodes.put(node.getId(), node);
      return this;
    }

    public ExecutionGraphBuilder join(final ExecutionNode.ExecutionNodeBuilder nodeBuilder,
        final Set<String> endNodeIds) {
      Verify.verify(endNodes.size() > 1, "Only single end node present");
      final ExecutionNode node = nodeBuilder.build();
      endNodeIds.forEach(new Consumer<String>() {
        @Override
        public void accept(final String nodeId) {
          endNodes.get(nodeId).addChildNode(node);
          endNodes.remove(nodeId);
        }
      });
      endNodes.put(node.getId(), node);
      return this;
    }

    public ExecutionGraph build() {

      validate();
      return new ExecutionGraph(name, root, endNodes.values().stream().collect(Collectors.toSet()));
    }

    /**
     * Should by non-cyclic graph
     */
    private void validate() {

    }

    private ExecutionNode getSingleEndNode() {
      Verify.verify(endNodes.size() == 1,
          "Have multiple end nodes");
      return endNodes.values().stream().findAny().get();
    }

  }

}
