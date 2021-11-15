package hoxtonr.frame.BinanceFrame.market;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.market.FundingRate;
import hoxtonr.frame.BinanceFrame.market.model.NextFundingRate;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.ArbitrageAccount;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinanceSwapFundingRateImp implements BinanceSwapFundingRate{
    @Override
    public List<FundingRate> BinanceLastFundingRate() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(ArbitrageAccount.getApiKey(),ArbitrageAccount.getSecretKey(),
                options);
        return syncRequestClient.getFundingRate(null,null,null,null);
    }

    @Override
    public List<NextFundingRate> BinanceNextFundingRate() throws IOException {
        String url = "https://fapi.binance.com/fapi/v1/premiumIndex ";
        List<NextFundingRate> rate = new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String c = response.body().string();
        JSONArray array = JSONArray.parseArray(c);
        for(int i = 0;i<array.size();i++) {
            JSONObject j = array.getJSONObject(i);
            NextFundingRate newrate = new NextFundingRate();
            newrate.setEstimatedSettlePrice(j.getString("estimatedSettlePrice"));
            newrate.setSymbol(j.getString("symbol"));
            newrate.setMarkPrice(j.getString("markPrice"));
            newrate.setIndexPrice(j.getString("indexPrice"));
            newrate.setTime(j.getLong("time"));
            newrate.setNextFundingTime(j.getLong("nextFundingTime"));
            newrate.setInterestRate(j.getString("interestRate"));
            newrate.setLastFundingRate(j.getString("lastFundingRate"));
            rate.add(newrate);
        }
        QuickSortRate(rate);
        return rate;
    }
    public void QuickSortRate(List<NextFundingRate> r){
        NextFundingRate temp;
        for (int i = 0; i < r.size(); i++) {//冒泡趟数
            for (int j = 0; j < r.size() - i - 1 ; j++) {
                if (Double.parseDouble(r.get(j+1).getLastFundingRate())<Double.parseDouble(r.get(j).getLastFundingRate())) {
                    temp = r.get(j);
                    r.set(j,r.get(j+1));
                    r.set(j+1,temp);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BinanceSwapFundingRate r = new BinanceSwapFundingRateImp();
        System.out.println(r.BinanceNextFundingRate());
    }
    
}
