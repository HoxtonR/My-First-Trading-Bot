package hoxtonr.frame.BinanceFrame.Account;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiFutureRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.FutureTransferType;
import com.binance.api.client.domain.account.AssetBalance;
import hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber.BinanceAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BinanceSpotAccountImp  implements BinanceSpotAccount{
    private String apikey;
    private String secretkey;
    public BinanceSpotAccountImp( String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    @Override
    public String BinanceSpotBalance(String cointype) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey,secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        // Get account balances
        com.binance.api.client.domain.account.Account account = client.getAccount(60_000L, System.currentTimeMillis());
        return account.getAssetBalance(cointype.replaceAll("USDT","")).getFree();
    }

    @Override
    public String BinanceSpotUsdt() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey,secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        com.binance.api.client.domain.account.Account account = client.getAccount(60_000L, System.currentTimeMillis());
        for(AssetBalance b : account.getBalances()) {
            if(b.getAsset().equals("USDT")){
            return b.getFree();
            }
        }
        return null;
    }
    @Override
    public String BinanceSpotBusd() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey,secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        com.binance.api.client.domain.account.Account account = client.getAccount(60_000L, System.currentTimeMillis());
        for(AssetBalance b : account.getBalances()) {
            if(b.getAsset().equals("BUSD")){
                return b.getFree();
            }
        }
        return null;
    }

    @Override
    public void BinanceSpotTransfer(String amount, FutureTransferType fu) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiFutureRestClient client = factory.newFutureRestClient();
        if(Double.parseDouble(amount) < 1){
            return;
        }
        BigDecimal b = BigDecimal.valueOf(Double.parseDouble(amount)).setScale(4,RoundingMode.DOWN);
        client.transferFuture("USDT",String.valueOf(amount), fu);
    }
    public static void main(String[] args){
        BinanceSpotAccount a = new BinanceSpotAccountImp(BinanceAccount.getApiKey(),BinanceAccount.getSecretKey());
        System.out.println(a.BinanceSpotBusd());
    }
}
