package hoxtonr.frame.FTXFrame.Market.FutureMarket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.FTXFrame.model.FTXMarket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTXFutureMarket {
    public final String MarketUrl = "https://ftx.com/api/futures";

    public String getFTXFutureMarket() throws IOException {
        Request request = new Request.Builder()
                .url(MarketUrl)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response re = okHttpClient.newCall(request).execute();
        if (re.isSuccessful() && re.body() != null) {
            String c = re.body().string();
            re.close();
            okHttpClient.connectionPool().evictAll();
            return c;
        } else {
            System.out.println("[FTX]价格获取失败，正在重新获取");
            re.close();
            okHttpClient.connectionPool().evictAll();
        }
        return null;
    }
    public List<FTXMarket> FTXSwapMarket() throws IOException {
        String c = getFTXFutureMarket();
        if(c!=null){
            List<FTXMarket> market = new ArrayList<>();
            JSONArray array = JSONObject.parseObject(c).getJSONArray("result");
            for(int i = 0;i<array.size();i++){
                FTXMarket tempmark = new FTXMarket();
                tempmark.setName(array.getJSONObject(i).getString("name"));
                tempmark.setLast(array.getJSONObject(i).getDouble("last"));
                tempmark.setPriceIncrement(array.getJSONObject(i).getDouble("priceIncrement"));
                tempmark.setSizeIncrement(array.getJSONObject(i).getDouble("sizeIncrement"));
                tempmark.setChange1h(array.getJSONObject(i).getDouble("change1h"));
                tempmark.setUnderlyingDescription(array.getJSONObject(i).getString("underlyingDescription"));
                tempmark.setPerpetual(array.getJSONObject(i).getBoolean("perpetual"));
                tempmark.setVolume(array.getJSONObject(i).getDouble("volume"));
                tempmark.setVolumeUsd24h(array.getJSONObject(i).getDouble("volumeUsd24h"));
                market.add(tempmark);
            }
            return market;
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        FTXFutureMarket market = new FTXFutureMarket();
        System.out.println(market.FTXSwapMarket());
    }
}


