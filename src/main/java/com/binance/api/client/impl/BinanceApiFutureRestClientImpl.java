package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiFutureRestClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.FutureTransferType;
import com.binance.api.client.domain.account.FutureTransaction;

import static com.binance.api.client.impl.BinanceApiServiceGenerator.createService;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

public class BinanceApiFutureRestClientImpl implements BinanceApiFutureRestClient {
    private final BinanceApiService binanceApiService;

    public BinanceApiFutureRestClientImpl(String apiKey, String secret) {
        binanceApiService = createService(BinanceApiService.class, apiKey, secret);
    }
    public FutureTransaction transferFuture(String asset, String amount, FutureTransferType type) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.transferFuture(asset, amount, type.getValue(), BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, timestamp));
    }
}
