package hoxtonr.frame.BinanceFrame.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Locale;

public class twitter_info {

    public String infoGeter() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                     .url("http://13.113.190.123/api/analyze/tweets?screenName=LatoyaM75223897")
                   // .url("http://13.113.190.123/api/analyze/tweets?screenName=coinbasepro")
                    .build();
            Response response = client.newCall(request).execute();
            String d = response.body().string();
            String f = d.substring(d.indexOf("tweets"));
            String[] tweet = f.split("}");
            String[] info = tweet[0].split(",\"");
            for (int i = 0; i < info.length; i++) {
                if (info[i].contains("text")) {
                    return info[i].replaceAll("text\":\"", "");
                }
            }
            Thread.sleep(200);
        } catch (Exception ignore) {

        }
        return null;
    }


    public boolean infodeterminer(String infor){
        String info = infor.toLowerCase(Locale.ROOT);
        if(info.contains("transfers")&&info.contains("available")){
            return true;
        }
        return false;
    }
    public String[] infohandler(String info){
        String c = "";
        if(info.contains("Transfers")){
            c = info.replace("Transfers","transfers");
        }else{
            c = info;
        }
        c = c.substring(c.indexOf("transfers"),c.indexOf("available"));

        if(info.contains("amp;")) {
            c = c.replace("amp;", "");
        }
        if(info.contains("and")){
            c = c.replace("and","&");
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i<c.length()){
            char chr = c.charAt(i);
            if(Character.isUpperCase(chr)||chr==','||chr=='&'){
                sb.append(chr);
            }
            i++;
        }
        String[] coin = sb.toString().split(",|&");
        for(int j=0;j<coin.length;j++){
            coin[j]+="USDT";
        }
        return coin;
    }
}
