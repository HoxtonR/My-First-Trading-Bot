package com.client;

import com.client.exception.BinanceApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

  void onError(BinanceApiException exception);
}
