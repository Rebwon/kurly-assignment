package com.rebwon.kurly.common.usecase;

public interface UseCase<T extends UseCaseRequest, S extends UseCaseResponse> {
  S execute(T request);
}
