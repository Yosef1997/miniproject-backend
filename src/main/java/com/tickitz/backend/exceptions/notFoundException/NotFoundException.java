package com.tickitz.backend.exceptions.notFoundException;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
