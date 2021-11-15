package hoxtonr.frame.BinanceFrame.market;


import com.alibaba.fastjson.JSONArray;
import hoxtonr.frame.BinanceFrame.BinanceInfoListener.BinanceSpotPriceListener;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class BinanceSpotMarketImp implements BinanceSpotMarket{
    @Override
    public HashMap<String,Double> BinanceSpotPrice() throws IOException {
        BinanceSpotPriceListener listener = new BinanceSpotPriceListener();
        OkHttpClient client = new OkHttpClient();
        String priceList = listener.BinanceSpotPrice(client);
        System.out.println(LocalDateTime.now());
        HashMap<String,Double> priceMap = new HashMap<>();
        JSONArray array = JSONArray.parseArray(priceList);
        for(int i = 0; i<array.size();i++){
           priceMap.put(array.getJSONObject(i).getString("symbol"),array.getJSONObject(i).getDouble("price"));
        }
        System.out.println(LocalDateTime.now());
        return priceMap;
    }
    public static void main(String[] args) throws IOException {
        BinanceSpotMarket market = new BinanceSpotMarketImp();
        System.out.println(market.BinanceSpotPrice());
    }
}
