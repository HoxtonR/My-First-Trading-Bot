package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class BinanceSpotPriceListener {

    public String BinanceSpotPrice(OkHttpClient client) throws IOException {
        String url = "https://api.binance.com/api/v3/ticker/price ";
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.body() != null) {
            String c = response.body().string();
            if (c.contains("BTCUSDT")) {
                client.connectionPool().evictAll();
                return c;
            } else {
                System.out.println("价格请求失败，正在重新请求！");
            }
        }
        client.connectionPool().evictAll();
        return null;
    }

    public static void main(String[] args) throws IOException {
        BinanceSpotPriceListener listen = new BinanceSpotPriceListener();

    }
}
