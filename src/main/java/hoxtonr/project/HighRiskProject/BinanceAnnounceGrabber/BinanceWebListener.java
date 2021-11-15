package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BinanceWebListener {
    public JSONArray infoGeter(CloseableHttpClient client,String url) {
        try {
            HttpGet get = new HttpGet(url);
            get.addHeader("Keep-Alive", "false");
            HttpResponse response = client.execute(get);
            String temp = EntityUtils.toString(response.getEntity());
            JSONObject o = JSONObject.parseObject(temp);
            if (!o.getString("code").equals("200")) {
                System.out.println("获取信息失败，正在重新获取！");
                return null;
            }
            JSONArray array = o.getJSONArray("data");
            return array;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<AnnounceModel> infoList(CloseableHttpClient client,String url){
        JSONArray a = infoGeter(client,url);
        if(a!=null){
            List<AnnounceModel> list = new ArrayList<>();
            for(int i = 0; i <a.size(); i++ ){
                AnnounceModel model = new AnnounceModel();
                model.setId(a.getJSONObject(i).getInteger("id"));
                model.setTitle((a.getJSONObject(i).getString("title")));
                model.setCachedAt(a.getJSONObject(i).getInteger("cachedAt"));
                model.setCatalogId(a.getJSONObject(i).getInteger("catalogId"));
                model.setCode(a.getJSONObject(i).getString("code"));
                list.add(model);
            }
            return list;
        }
        return null;
    }
    public Boolean infoDeterminer(String info2){
        String info = info2.toLowerCase(Locale.ROOT);
        return (info.contains("binance")&&info.contains("adds")&&info.contains("trading")&&info.contains("pairs"))||
                (info.contains("support")&&info.contains("binance")&&info.contains("will"))||
                (info.contains("trade")&&(info.contains("win")||info.contains("rewards")))||
                (info.contains("staking")&&info.contains("rewards"));
    }
    public List<String> infoHandler(String info2) throws IOException {
        String info = info2.toLowerCase(Locale.ROOT);
        List<String> returncoin = new ArrayList<>();
        if(info.contains("binance")&&info.contains("adds")&&info.contains("trading")&&info.contains("pairs")){
            String tempinfo = info2.replace("Binance","").replace("Adds","").replace("Trading","").replace("Pairs","").replaceAll(" ","");
            String[] coin = tempinfo.split(",|&");
            for(String c : coin) {
                if(c.contains("USDT")){
                    returncoin.add(c.replace("/USDT","BUSD"));
                }
            }
        }
        if(info.contains("support")&&info.contains("binance")&&info.contains("will")){
            if(info.contains("airdrop")){
                info = info2.substring(info.indexOf("airdrop"));
                String objStr=info.substring(info.indexOf("(")+1,info.indexOf(")"));
                returncoin.add(objStr+"USDT");
            }
            if(info.contains("network")&&info.contains("upgrade")){
                String objStr=info2.substring(info.indexOf("(")+1,info.indexOf(")"));
                returncoin.add(objStr+"USDT");
            }
        }
        if(info.contains("trade")&&(info.contains("win")||info.contains("rewards"))){
            String tempInfo = info2.substring(info2.indexOf("Trade ")).replace("Trade ","");
            String tempInfo2 = tempInfo.substring(0,tempInfo.indexOf(" "));
            returncoin.add(tempInfo2+"USDT");
        }
        if(info.contains("staking")&&info.contains("rewards")){
            String tempInfo = info2.substring(0,info2.indexOf("Staking")).replace(" ","");
            returncoin.add(tempInfo+"USDT");
        }
        return returncoin;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        BinanceWebListener w= new BinanceWebListener();
        CloseableHttpClient client = HttpClients.custom()
                .evictExpiredConnections()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .build();
        System.out.println(w.infoList(client,"http://54.150.5.108:3862/api/announcements"));
    }
}
