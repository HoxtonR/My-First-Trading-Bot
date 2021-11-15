package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

import java.io.File;
import java.io.IOException;

public class BinanceSpotAccountListener {
    private String username;
    private String apikey;
    private String secretkey;

    public BinanceSpotAccountListener(String username, String apikey, String secretkey) {
        this.username = username;
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    public String BinanceSpotAccount() throws IOException {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        String Filename = username + "-BinanceBalanceSpot.json";
        File file = new File(Filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        com.binance.api.client.domain.account.Account account = client.getAccount(60_000L, System.currentTimeMillis());
        return account.getAssetBalance("USDT").getFree();
    }
}
