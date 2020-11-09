package com.rebwon.kurly.common.exception;

public class SystemException extends RuntimeException {

  public SystemException() {
  }

  public SystemException(String message) {
    super(message);
  }

  public SystemException(String message, Throwable cause) {
    super(message, cause);
  }
}
