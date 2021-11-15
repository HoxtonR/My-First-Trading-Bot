package hoxtonr.frame.BinanceFrame.market;

import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.market.FundingRate;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.ArbitrageAccount;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.BinanceApiKey;

import java.util.List;

public class BinanceSwapMarketImp implements BinanceSwapMarket{
    @Override
    public String BinanceSwapMarketPrice(String cointype){
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(ArbitrageAccount.getApiKey(),ArbitrageAccount.getSecretKey(),
                options);
        return String.valueOf(syncRequestClient.getMarkPrice(cointype).get(0).getMarkPrice().doubleValue());
    }
    @Override
    public List<FundingRate> BinanceFundingRate(String apikey, String secretkey){
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey,secretkey,
                options);
        return syncRequestClient.getFundingRate(null,null,null,null);
    }

    public static void QuickSort(List<FundingRate> r) {
        FundingRate temp;
        for (int i = 0; i < r.size(); i++) {
            for (int j = 0; j < r.size() - i - 1; j++) {
                if (r.get(j+1).getFundingRate().doubleValue()<r.get(j).getFundingRate().doubleValue()) {
                    temp = r.get(j);
                    r.set(j,r.get(j+1));
                    r.set(j+1,temp);
                }
            }
        }
    }
    public static void main(String[] args){
        BinanceSwapMarket m = new BinanceSwapMarketImp();
        List<FundingRate> rate = m.BinanceFundingRate(BinanceApiKey.getApikey(),BinanceApiKey.getSecretkey());
        QuickSort(rate);
        System.out.println(rate);
        System.out.println(m.BinanceSwapMarketPrice("BTCUSDT"));

    }

}
