package com.bsb.mobilenotificationservice.common.messenger;

import java.util.concurrent.CompletableFuture;

public interface MessengerStrategy<T> {
  CompletableFuture<Boolean> send(T request);
  String getMessengerType();
}