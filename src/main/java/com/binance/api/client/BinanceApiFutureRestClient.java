package com.binance.api.client;

import com.binance.api.client.domain.FutureTransferType;
import com.binance.api.client.domain.account.FutureTransaction;

public interface BinanceApiFutureRestClient {

    FutureTransaction transferFuture(String asset, String amount, FutureTransferType type);

}