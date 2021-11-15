package hoxtonr.frame.Tools;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class HuobiJsonHandler {
    public List<String> HuobiJsonHand(String Filename) {
        Outer: while (true) {
            String[] tempString = new String[1000];
            FileReader reader = new FileReader();
            int temp1 = 0;
            int temp2 = 0;
            for (int i = 0; i < 2; i++) {
                String temp = reader.readFileByByte(Filename);
                if (!temp.contains("data")) {
                    continue Outer;
                }
                tempString = temp.substring(temp.indexOf("data")).split("}");
                if (i == 0) {
                    temp1 = tempString.length;
                }
                if (i == 1) {
                    temp2 = tempString.length;
                }
                if(temp1!=0&&temp2!=0){
                    if(temp1==temp2&&tempString.length>900){
                        break;
                    }else{
                        System.out.println("路径1");
                        continue Outer;
                    }
                }
            }
                return Arrays.asList(tempString);
            }
        }
    public List<String> HuobiSwapJsonHand(String Filename) {
        Outer: while (true) {
            String[] tempString = new String[1000];
            FileReader reader = new FileReader();
            int temp1 = 0;
            int temp2 = 0;
            for (int i = 0; i < 2; i++) {
                String temp = reader.readFileByByte(Filename);
                if (!temp.contains("ticks")) {
                    continue Outer;
                }
                tempString = temp.substring(temp.indexOf("ticks")).split("}");
                if (i == 0) {
                    temp1 = tempString.length;
                }
                if (i == 1) {
                    temp2 = tempString.length;
                }
                if(temp1!=0&&temp2!=0){
                    if(temp1==temp2){
                        break;
                    }else{
                        System.out.println("路径1");
                        continue Outer;
                    }
                }
            }
            return Arrays.asList(tempString);
        }
    }
    public JSONArray TradeRuleJsonReader(String Filename) {
        FileReader reader = new FileReader();
        String json = reader.readFileByByte(Filename);
        JSONArray object = JSONObject.parseObject(json).getJSONArray("data");
        return object;
    }

    public Integer[] HuobiSpotTradeRuleHandler(String cointype) {
        Integer[] prcision = new Integer[2];
        JSONArray conlist = TradeRuleJsonReader("HuobiSpotTradeRule.json");
        for (int i = 0; i < conlist.size(); i++) {
            String c = conlist.getJSONObject(i).getString("symbol");
            if (c.equals(cointype)) {
                prcision[0] = Integer.parseInt(conlist.getJSONObject(i).getString("price-precision"));
                prcision[1] = Integer.parseInt(conlist.getJSONObject(i).getString("amount-precision"));
            }
        }
        return prcision;
    }

    public int HuobiSpotTradeRuleHandlerQuanOnly(String cointype) {
        int prcision = 0;
        JSONArray conlist = TradeRuleJsonReader("HuobiSpotTradeRule.json");
        for (int i = 0; i < conlist.size(); i++) {
            String c = conlist.getJSONObject(i).getString("symbol");
            if (c.equals(cointype)) {
                prcision = Integer.parseInt(conlist.getJSONObject(i).getString("amount-precision"));
            }
        }
        return prcision;
    }

    public BigDecimal[] HuobiSwapTradeRuleHandler(String cointype) {
        BigDecimal[] prcision = new BigDecimal[2];
        JSONArray conlist = TradeRuleJsonReader("HuobiSwapTradeRule.json");
        for (int i = 0; i < conlist.size(); i++) {
            String c = conlist.getJSONObject(i).getString("contract_code");
            if (c.equals(cointype)) {
                try {
                    String pricefilter2 = conlist.getJSONObject(i).getString("price_tick");
                    prcision[0] = BigDecimal.valueOf(pricefilter2.substring(pricefilter2.indexOf("."), pricefilter2.indexOf("1")).length());
                } catch (StringIndexOutOfBoundsException e) {
                    prcision[0] = BigDecimal.valueOf(0);
                }
                prcision[1] = BigDecimal.valueOf(Double.parseDouble(conlist.getJSONObject(i).getString("contract_size")));
            }
        }
        return prcision;
    }

    public BigDecimal HuobiSwapTradeRuleHandlerQuanOnly(String cointype) {
        BigDecimal prcision = new BigDecimal(0);
        JSONArray conlist = TradeRuleJsonReader("HuobiSwapTradeRule.json");
        for (int i = 0; i < conlist.size(); i++) {
            String c = conlist.getJSONObject(i).getString("contract_code");
            if (c.equals(cointype)) {
                prcision = BigDecimal.valueOf(Double.parseDouble(conlist.getJSONObject(i).getString("contract_size")));
            }
        }
        return prcision;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now());
        HuobiJsonHandler jsonhand = new HuobiJsonHandler();
        List<String> a = jsonhand.HuobiSwapJsonHand("HuobiSwapPrice.json");
        System.out.println(a.size());
        System.out.println(LocalDateTime.now());
    }
}
