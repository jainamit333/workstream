
package com.horizon.workstream.elements.step;

public class VoidResponse implements StepResponse {

  private final Long createdAt;

  public VoidResponse() {
    createdAt = System.currentTimeMillis();
  }

  @Override
  public long createdAt() {
    return createdAt;
  }
}
