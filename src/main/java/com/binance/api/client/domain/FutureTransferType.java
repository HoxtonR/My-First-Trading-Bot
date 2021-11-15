package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public enum FutureTransferType {
    SPOT_TO_USDTSWAP("1"),USDTSWAP_TO_SPOT("2"),SPOT_TO_COINBASESWAP("3"),COINBASESWAP_TO_SPOT("4");

    private String value;

    FutureTransferType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
