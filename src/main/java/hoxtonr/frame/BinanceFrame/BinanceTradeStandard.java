package hoxtonr.frame.BinanceFrame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.BinanceFrame.BinanceInfoListener.BinanceSpotTradeRuleListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class BinanceTradeStandard {
    public HashMap<String,String> JsonHandle(String BinancePriceStr) {
        HashMap<String, String> coinHash = new HashMap<>();
        JSONArray array = JSONObject.parseArray(BinancePriceStr);
        for(int i = 0; i<array.size();i++){
            JSONObject j = array.getJSONObject(i);
            coinHash.put(j.getString("symbol"),j.getString("price"));
        }
        return coinHash;
    }
    public HashMap<String,Integer[]> TradeRuleHandler(String BinanceSpotTradeRule) {
        HashMap<String,Integer[]> spotTradeRuleHash = new HashMap<>();
        JSONObject BinSpotTradeRule = JSONObject.parseObject(BinanceSpotTradeRule);
        JSONArray coinlist = JSONArray.parseArray(BinSpotTradeRule.getString("symbols"));
        for(int i = 0; i<coinlist.size();i++){
            Integer[] mainhash = new Integer[2];
            JSONArray o = coinlist.getJSONObject(i).getJSONArray("filters");
            for(int j = 0;j<o.size();j++){
                JSONObject m = o.getJSONObject(j);
                if(m.getString("filterType").equals("LOT_SIZE")){
                    String c = m.getString("stepSize");
                    int decimal = c.indexOf("1")-c.indexOf(".");
                    if(decimal>0){
                    mainhash[0] = decimal;
                    }else{
                       mainhash[0] = 0;
                    }
                }
                if(m.getString("filterType").equals("PRICE_FILTER")){
                    String c = m.getString("tickSize");
                    int decimal = c.indexOf("1")-c.indexOf(".");
                    if(decimal>0){
                        mainhash[1] = decimal;
                    }else{
                        mainhash[1] = 0;
                    }
                }
            }
            spotTradeRuleHash.put(coinlist.getJSONObject(i).getString("symbol"),mainhash);
        }
        return spotTradeRuleHash;
    }
    public static void main(String[] args) throws IOException {
        BinanceTradeStandard s = new BinanceTradeStandard();
        BinanceSpotTradeRuleListener l = new BinanceSpotTradeRuleListener();
        HashMap<String,Integer[]> hs = s.TradeRuleHandler(l.BinanceSpotTradeRule());
        for(String c : hs.keySet()){
            System.out.print(c + "");
            System.out.print(Arrays.toString(hs.get(c)));
        }
    }
}
