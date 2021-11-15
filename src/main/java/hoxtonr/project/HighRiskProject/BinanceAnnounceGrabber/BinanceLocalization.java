package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;

import com.alibaba.fastjson.JSONArray;

import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccount;
import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccountImp;
import hoxtonr.frame.BinanceFrame.BinanceInfoListener.BinanceSpotPriceListener;
import hoxtonr.frame.BinanceFrame.BinanceInfoListener.BinanceSpotTradeRuleListener;
import hoxtonr.frame.BinanceFrame.BinanceTradeStandard;
import okhttp3.OkHttpClient;

import java.io.*;
import java.util.HashMap;

public class BinanceLocalization {

    private String readFile(String filePath) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] buffer = new byte[2048];
        int count = 0;
        try {
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            while (-1 != (count = inputStream.read(buffer))) {
                stringBuffer.append(new String(buffer, 0, count));
            }
            inputStream.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeFile(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void priceLocalization(OkHttpClient client){
        BinanceSpotPriceListener price = new BinanceSpotPriceListener();
        try {
            writeFile(price.BinanceSpotPrice(client), "BinanceSpotPrice.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tradeRuleLocalization(){
        BinanceSpotTradeRuleListener tradeRule = new BinanceSpotTradeRuleListener();
        try {
            writeFile(tradeRule.BinanceSpotTradeRule(), "BinanceSpotTradeRule.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void balanceLocalization(){
        BinanceSpotAccount account = new BinanceSpotAccountImp(BinanceAccount.getApiKey(),BinanceAccount.getSecretKey());
        writeFile(account.BinanceSpotUsdt(), "Vida-BinanceBalanceSpot.json");
    }
    public String balanceGeter(){
        return readFile("Vida-BinanceBalanceSpot.json");
    }
    public HashMap<String,Double> priceHandler(){
        String price = readFile("BinanceSpotPrice.json");
        HashMap<String,Double> priceMap = new HashMap<>();
        JSONArray array = JSONArray.parseArray(price);
        for(int i = 0; i<array.size();i++){
            priceMap.put(array.getJSONObject(i).getString("symbol"),array.getJSONObject(i).getDouble("price"));
        }
        return priceMap;
    }
    public HashMap<String,Integer[]> tradeRuleHandler(){
        String tradeRule = readFile("BinanceSpotTradeRule.json");
        BinanceTradeStandard std = new BinanceTradeStandard();
        return std.TradeRuleHandler(tradeRule);
    }
    public static void main(String[] args) {
        BinanceLocalization li = new BinanceLocalization();
        System.out.println(li.tradeRuleHandler().get("BTCUSDT")[0]);
    }

}
