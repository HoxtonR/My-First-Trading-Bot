package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class BinanceSwapPriceListener {

    public String BinanceSwapPrice() throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("13.231.28.14", 8888)));
        final String userName = "emproxy";
        final String password = "Yy3BwC3nQePEBqBWWAyEtbPLzxnfJ8qH";
        builder.proxyAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(userName, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        });
        String url = "https://fapi.binance.com/fapi/v1/ticker/price";
        OkHttpClient okHttpClient = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.body() != null) {
            String c = response.body().string();
            if (c.contains("BTCUSDT")) {
                okHttpClient.connectionPool().evictAll();
                return c;
            } else {
                System.out.println("价格请求失败，正在重新请求！");
            }
        }
        okHttpClient.connectionPool().evictAll();
        return null;
    }



}
