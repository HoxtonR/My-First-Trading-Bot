package hoxtonr.frame.FTXFrame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.FTXFrame.Market.FutureMarket.FTXFutureMarket;

import java.io.IOException;
import java.util.HashMap;

public class FTXTradeStandard {
    public HashMap<String,String> FTXSwapPriceJsonHandler(String FTXSwapPriceStr){
        HashMap<String,String> priceHash = new HashMap<>();
        JSONObject o = JSONObject.parseObject(FTXSwapPriceStr);
        JSONArray array = JSONArray.parseArray(o.getString("result"));
        for(int i = 0; i<array.size();i++){
            JSONObject obj = array.getJSONObject(i);
            priceHash.put(obj.getString("name"),obj.getString("mark"));
        }
        return priceHash;
    }
    public HashMap<String,String[]> FTXSwapTradeRule(String FTXSwapTradeRuleStr){
        HashMap<String,String[]> tradeRuleHash = new HashMap<>();
        JSONObject o = JSONObject.parseObject(FTXSwapTradeRuleStr);
        JSONArray array = JSONArray.parseArray(o.getString("result"));
        for(int i = 0; i<array.size();i++){
            String[] c = new String[2];
            JSONObject obj = array.getJSONObject(i);
            c[0] = obj.getString("priceIncrement");
            c[1] = obj.getString("sizeIncrement");
            tradeRuleHash.put(obj.getString("name"),c);
        }
        return tradeRuleHash;
    }
    public static void main(String[] args) throws IOException {
        FTXTradeStandard s = new FTXTradeStandard();
        FTXFutureMarket market = new FTXFutureMarket();
        System.out.println((s.FTXSwapPriceJsonHandler(market.getFTXFutureMarket()).get("BTC-PERP")));
    }
}
