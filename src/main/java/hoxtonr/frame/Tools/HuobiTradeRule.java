package hoxtonr.frame.Tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class HuobiTradeRule {
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

    public static void main(String[] args){
        HuobiTradeRule r = new HuobiTradeRule();
        FileReader read = new FileReader();

        System.out.println(r.SwapPriceJsonHandler(read.readFileByByte("HuobiSwapPrice.json")));
    }
}
