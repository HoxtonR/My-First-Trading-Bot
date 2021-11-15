package hoxtonr.frame.HuobiFrame.HuobiInfoListener;

import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class HuobiSwapTradeRuleListener {

    public String HuobiSwapTradeRule() throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("13.113.214.16", 8888)));
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
        String url = "https://api.hbdm.com/linear-swap-api/v1/swap_contract_info";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        String c = "";
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        c = response.body().string();
        if (response.body() != null) {
            return c;
        }
        return null;
    }

}

