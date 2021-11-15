package hoxtonr.frame.FTXFrame.Market.FutureMarket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.FTXFrame.model.FTXLastFundingRate;
import hoxtonr.frame.FTXFrame.model.FTXNextFundingRate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTXFundingRate {
    private final String fundingrateUrl = "https://ftx.com/api/funding_rates";

    private String getFTXFundingRate() throws IOException {
        Request request = new Request.Builder()
                .url(fundingrateUrl)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response re = okHttpClient.newCall(request).execute();
        if (re.isSuccessful() && re.body() != null) {
            String c =re.body().string();
            re.close();
            okHttpClient.connectionPool().evictAll();
            return c;
        } else {
            System.out.println("[FTX]资金费率获取失败，正在重新获取");
            re.close();
            okHttpClient.connectionPool().evictAll();
        }
        return null;
    }

    public List<FTXLastFundingRate> FTXSortedFundingRate() throws IOException {
        JSONArray o = JSONObject.parseObject(getFTXFundingRate()).getJSONArray("result");
        List<FTXLastFundingRate> rate = new ArrayList<>();
        for (int i = 0; i < o.size(); i++) {
            JSONObject a = o.getJSONObject(i);
            FTXLastFundingRate temprate = new FTXLastFundingRate();
            temprate.setFuture(a.getString("future"));
            temprate.setTime(a.getString("time"));
            temprate.setRate(a.getDouble("rate"));
            rate.add(temprate);
        }
        QuickSort(rate);
        return rate;
    }
    private String getNextFundingRate(String cointype) throws IOException {
        String url = "https://ftx.com/api/futures/"+cointype+"/stats";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response re = okHttpClient.newCall(request).execute();
        if (re.isSuccessful() && re.body() != null) {
           String c =re.body().string();
           re.close();
           okHttpClient.connectionPool().evictAll();
           return c;
        }
        re.close();
        okHttpClient.connectionPool().evictAll();
        return null;
    }
    public FTXNextFundingRate FTXNextFundingRate(String cointype) throws IOException {
        String c = getNextFundingRate(cointype);
        if(c!=null){
        JSONObject o = JSONObject.parseObject(c).getJSONObject("result");
        FTXNextFundingRate r = new FTXNextFundingRate();
            r.setNextFundingRate(o.getDouble("nextFundingRate"));
            r.setNextFundingTime(o.getString("nextFundingTime"));
            r.setOpenInterest(o.getDouble("openInterest"));
            r.setVolume(o.getDouble("volume"));
            return r;
        }
        return null;
    }
    private void QuickSort(List<FTXLastFundingRate> r) throws IOException {
        FTXLastFundingRate temp;
        for (int i = 0; i < r.size(); i++) {//冒泡趟数
            for (int j = 0; j < r.size() - i - 1 ; j++) {
                if ((r.get(j+1).getRate()<r.get(j).getRate())) {
                    temp = r.get(j);
                    r.set(j,r.get(j+1));
                    r.set(j+1,temp);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        FTXFundingRate rate = new FTXFundingRate();
        System.out.println(rate.FTXSortedFundingRate());
    }
}
