package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.trade.AccountBalance;

import java.math.BigDecimal;

public class BinanceSwapAccountListener {
    private final String username;
    private String apikey;
    private String secretkey;

    public BinanceSwapAccountListener(String username, String apikey, String secretkey) {
        this.username = username;
        this.apikey = apikey;
        this.secretkey = secretkey;
    }


    public BigDecimal BinanceSwapAccount() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey, options);
        for (AccountBalance c : syncRequestClient.getBalance()) {
            if (c.getAsset().equals("USDT")) {
                return c.getBalance();
            }
        }
        return null;
    }
}
