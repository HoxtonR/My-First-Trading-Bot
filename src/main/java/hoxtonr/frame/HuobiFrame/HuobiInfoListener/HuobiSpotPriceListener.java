package hoxtonr.frame.HuobiFrame.HuobiInfoListener;

import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class HuobiSpotPriceListener {

    public String HuobiSpotPrice() throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("52.194.211.0", 8888)));
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
        String url = "https://api.huobi.pro/market/tickers ";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        String c = "";
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.body() != null) {
            c = response.body().string();
            if (c.contains("data")) {
                return c;
            } else {
                System.out.println("价格请求失败，正在重新请求！");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HuobiSpotPriceListener p = new HuobiSpotPriceListener();

    }
}


