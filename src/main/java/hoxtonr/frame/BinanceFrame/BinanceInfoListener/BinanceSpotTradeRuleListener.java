package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import okhttp3.*;

import java.io.IOException;

public class BinanceSpotTradeRuleListener {
    public String BinanceSpotTradeRule() throws IOException {
        String url = "https://api.binance.com/api/v3/exchangeInfo ";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if (response.body() != null) {
                return response.body().string();
            }
        return null;
    }
}

