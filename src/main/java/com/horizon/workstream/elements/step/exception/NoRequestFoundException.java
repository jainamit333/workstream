
package com.horizon.workstream.elements.step.exception;

public class NoRequestFoundException extends RuntimeException {

  public NoRequestFoundException() {
  }

  public NoRequestFoundException(final String message) {
    super(message);
  }

  public NoRequestFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public NoRequestFoundException(final Throwable cause) {
    super(cause);
  }

  public NoRequestFoundException(final String message, final Throwable cause, final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
