package hoxtonr.frame.Tools;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;


public class BinanceJsonHandler {
    public HashMap<String,String> JsonHandle(String BinancePriceStr) {
            HashMap<String, String> coinHash = new HashMap<>();
            JSONArray array = JSONObject.parseArray(BinancePriceStr);
            for(int i = 0; i<array.size();i++){
                JSONObject j = array.getJSONObject(i);
                coinHash.put(j.getString("symbol"),j.getString("price"));
            }
            return coinHash;
    }
    public JSONArray TradeRuleJsonReader(String Filename) {
        FileReader reader = new FileReader();
        String json = reader.readFileByByte(Filename);
        JSONArray object = JSONObject.parseObject(json).getJSONArray("symbols");
        return object;
    }
    public Integer[] SpotTradeRuleHandler(String cointype) {
        Integer[] precision = new Integer[2];
        JSONArray coinlist = TradeRuleJsonReader("BinanceSpotTradeRule.json");
        for (int i = 0; i < coinlist.size(); i++) {
            String temp = coinlist.getJSONObject(i).getString("symbol");
            if (temp.equals(cointype)) {
                JSONArray array = coinlist.getJSONObject(i).getJSONArray("filters");
                for (int j = 0; j < array.size(); j++) {
                    String temp2 = array.getJSONObject(j).getString("filterType");
                    if (temp2.equals("PRICE_FILTER")) {
                        String pricefilter = array.getJSONObject(j).getString("tickSize");
                        precision[0] = pricefilter.substring(pricefilter.indexOf("."), pricefilter.indexOf("1")).length();
                    }
                    if (temp2.equals("LOT_SIZE")) {
                        String pricefilter2 = array.getJSONObject(j).getString("stepSize");
                        try {
                            precision[1] = pricefilter2.substring(pricefilter2.indexOf("."), pricefilter2.indexOf("1")).length();
                        } catch (StringIndexOutOfBoundsException e) {
                            precision[1] = 0;
                        }
                    }
                }
            }
        }
        return precision;
    }

    public int SpotTradeRuleHandlerQuanOnly(String cointype) {
        int precision = 0;
        JSONArray coinlist = TradeRuleJsonReader("BinanceSpotTradeRule.json");
        for (int i = 0; i < coinlist.size(); i++) {
            String temp = coinlist.getJSONObject(i).getString("symbol");
            if (temp.equals(cointype)) {
                JSONArray array = coinlist.getJSONObject(i).getJSONArray("filters");
                for (int j = 0; j < array.size(); j++) {
                    String temp2 = array.getJSONObject(j).getString("filterType");
                    if (temp2.equals("LOT_SIZE")) {
                        String pricefilter2 = array.getJSONObject(j).getString("stepSize");
                        try {
                            precision = pricefilter2.substring(pricefilter2.indexOf("."), pricefilter2.indexOf("1")).length();
                        } catch (StringIndexOutOfBoundsException e) {
                            precision = 0;
                        }
                    }
                }
            }
        }
        return precision;
    }

    public Integer[] SwapTradeRuleHandler(String cointype) {
        Integer[] precision = new Integer[2];
        JSONArray coinlist = TradeRuleJsonReader("BinanceSwapTradeRule.json");
        for (int i = 0; i < coinlist.size(); i++) {
            String temp = coinlist.getJSONObject(i).getString("symbol");
            if (temp.equals(cointype)) {
                JSONArray array = coinlist.getJSONObject(i).getJSONArray("filters");
                for (int j = 0; j < array.size(); j++) {
                    String temp2 = array.getJSONObject(j).getString("filterType");
                    if (temp2.equals("PRICE_FILTER")) {
                        String pricefilter = array.getJSONObject(j).getString("tickSize");
                        precision[0] = pricefilter.substring(pricefilter.indexOf("."), pricefilter.indexOf("1")).length();
                    }
                    if (temp2.equals("LOT_SIZE")) {
                        String pricefilter2 = array.getJSONObject(j).getString("stepSize");
                        try {
                            precision[1] = pricefilter2.substring(pricefilter2.indexOf("."), pricefilter2.indexOf("1")).length();
                        } catch (StringIndexOutOfBoundsException e) {
                            precision[1] = 0;
                        }
                    }
                }
            }
        }
        return precision;
    }

    public int SwapTradeRuleHandlerQuanOnly(String cointype) {
        int precision = 0;
        JSONArray coinlist = TradeRuleJsonReader("BinanceSwapTradeRule.json");
        for (int i = 0; i < coinlist.size(); i++) {
            String temp = coinlist.getJSONObject(i).getString("symbol");
            if (temp.equals(cointype)) {
                JSONArray array = coinlist.getJSONObject(i).getJSONArray("filters");
                for (int j = 0; j < array.size(); j++) {
                    String temp2 = array.getJSONObject(j).getString("filterType");
                    if (temp2.equals("LOT_SIZE")) {
                        String pricefilter2 = array.getJSONObject(j).getString("stepSize");
                        try {
                            precision = pricefilter2.substring(pricefilter2.indexOf("."), pricefilter2.indexOf("1")).length();
                        } catch (StringIndexOutOfBoundsException e) {
                            precision = 0;
                        }
                    }
                }
            }
        }
        return precision;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now());
        BinanceJsonHandler hand = new BinanceJsonHandler();

        HashMap<String,String> c = hand.JsonHandle("[{\"symbol\":\"ETHBTC\",\"price\":\"0.07219000\"},{\"symbol\":\"LTCBTC\",\"price\":\"0.00389600\"},{\"symbol\":\"BNBBTC\",\"price\":\"0.00898500\"},{\"symbol\":\"NEOBTC\",\"price\":\"0.00108300\"}]");
        System.out.println(c);
        System.out.println(LocalDateTime.now());
        System.out.println(hand.SwapTradeRuleHandlerQuanOnly("BTCUSDT"));
        System.out.println(LocalDateTime.now());

    }
}
