package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import okhttp3.*;

import java.io.IOException;

public class BinanceSwapTradeRuleListener{

    public String BinanceSwapTradeRule() throws IOException {
        String url = "https://fapi.binance.com/fapi/v1/exchangeInfo ";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String c = response.body().string();
        if (response.body() != null) {
            okHttpClient.connectionPool().evictAll();
            return c;
        }
        okHttpClient.connectionPool().evictAll();
        return null;
    }
    public static void main(String[] args) throws IOException {
        BinanceSwapTradeRuleListener r = new BinanceSwapTradeRuleListener();
        System.out.println(r.BinanceSwapTradeRule());
    }
}
