package hoxtonr.frame.Tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;

public class BinanceTradeRule {
    public HashMap<String,String> JsonHandle(String BinancePriceStr) {
        HashMap<String, String> coinHash = new HashMap<>();
        JSONArray array = JSONObject.parseArray(BinancePriceStr);
        for(int i = 0; i<array.size();i++){
            JSONObject j = array.getJSONObject(i);
            coinHash.put(j.getString("symbol"),j.getString("price"));
        }
        return coinHash;
    }
    public HashMap<String,String[]> TradeRuleHandler(String BinanceSpotTradeRule) {
        HashMap<String,String[]> spotTradeRuleHash = new HashMap<>();
        String[] mainhash = new String[2];
        JSONObject BinSpotTradeRule = JSONObject.parseObject(BinanceSpotTradeRule);
        JSONArray coinlist = JSONArray.parseArray(BinSpotTradeRule.getString("symbols"));
        for(int i = 0; i<coinlist.size();i++){
            JSONArray o = coinlist.getJSONObject(i).getJSONArray("filters");
            for(int j = 0;j<o.size();j++){
                JSONObject m = o.getJSONObject(j);
                if(m.get("filterType").equals("LOT_SIZE")){
                    mainhash[0] = m.getString("stepSize");
                }
                if(m.get("filterType").equals("PRICE_FILTER")){
                    mainhash[1] = m.getString("tickSize");
                }
                spotTradeRuleHash.put(coinlist.getJSONObject(i).getString("symbol"),mainhash);
            }
        }
        return spotTradeRuleHash;
    }
    public static void main(String[] args){
        System.out.println(LocalDateTime.now());
        BinanceTradeRule r = new BinanceTradeRule();
        System.out.println(LocalDateTime.now());
        FileReader read = new FileReader();
        System.out.println(LocalDateTime.now());
        String txt = read.readFileByByte("BinanceSwapTradeRule.json");
        System.out.println(LocalDateTime.now());
        HashMap<String, String[]> s = r.TradeRuleHandler(txt);
        System.out.println(LocalDateTime.now());
        System.out.println(s.get("BTCUSDT")[1]);
        System.out.println(LocalDateTime.now());


    }

}
