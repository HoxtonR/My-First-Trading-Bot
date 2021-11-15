package hoxtonr.frame.FTXFrame.Market.SpotMarket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class FTXSpotMarket {
    public final String MarketUrl = "https://ftx.com/api/markets";

    private String getFTXSpotMarket() throws IOException {
        Request request = new Request.Builder()
                .url(MarketUrl)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response re = okHttpClient.newCall(request).execute();
        if(re.isSuccessful()&&re.body()!=null) {
            return re.body().string();
        }else{
            System.out.println("[FTX]价格获取失败，正在重新获取");
        }
        return null;
    }
}
