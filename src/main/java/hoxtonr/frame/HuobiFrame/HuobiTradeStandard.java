package hoxtonr.frame.HuobiFrame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.Tools.FileReader;

import java.util.Arrays;
import java.util.HashMap;

public class HuobiTradeStandard {
    public HashMap<String,String> SpotPriceJsonHandler(String HuobiSpotPriceStr){
        HashMap<String,String> priceHash = new HashMap<>();
        JSONObject o = JSONObject.parseObject(HuobiSpotPriceStr);
        JSONArray array = JSONObject.parseArray(o.getString("data"));
        for(int i = 0; i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            priceHash.put(js.getString("symbol"),js.getString("close"));
        }
        return priceHash;
    }
    public HashMap<String,String> SwapPriceJsonHandler(String HuobiSwapPriceStr){
        HashMap<String,String> priceHash = new HashMap<>();
        JSONObject o = JSONObject.parseObject(HuobiSwapPriceStr);
        JSONArray array = JSONObject.parseArray(o.getString("ticks"));
        for(int i = 0; i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            priceHash.put(js.getString("contract_code"),js.getString("close"));
        }
        return priceHash;
    }
    public HashMap<String,String[]> SpotTradeRule(String HuobiSpotPriceStr){
        HashMap<String,String[]> spotTradeRule = new HashMap<>();
        JSONObject o = JSONObject.parseObject(HuobiSpotPriceStr);
        JSONArray array = JSONArray.parseArray(o.getString("data"));
        for(int i = 0; i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            String[] spotrulearray = new String[2];
            spotrulearray[0] = js.getString("price-precision");
            spotrulearray[1] = js.getString("amount-precision");
            spotTradeRule.put(js.getString("symbol"),spotrulearray);
        }
        return spotTradeRule;
    }
    public HashMap<String,String[]> SwapTradeRule(String HuobiSwapPriceStr){
        HashMap<String,String[]> swapTradeRule = new HashMap<>();
        JSONObject o = JSONObject.parseObject(HuobiSwapPriceStr);
        JSONArray array = JSONArray.parseArray(o.getString("data"));
        for(int i = 0; i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            String[] swaptrulearray = new String[2];
            swaptrulearray[0] = js.getString("price_tick");
            swaptrulearray[1] = js.getString("contract_size");
            swapTradeRule.put(js.getString("contract_code"),swaptrulearray);
        }
        return swapTradeRule;
    }
    public static void main(String[] args){
        HuobiTradeStandard s = new HuobiTradeStandard();
        FileReader r = new FileReader();
        System.out.println(Arrays.toString(s.SwapTradeRule(r.readFileByByte("HuobiSwapTradeRule.json")).get("BTC-USDT")));
    }

}
