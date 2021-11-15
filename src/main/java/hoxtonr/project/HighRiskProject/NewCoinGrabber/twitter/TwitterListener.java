package hoxtonr.project.HighRiskProject.NewCoinGrabber.twitter;

import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

public class TwitterListener {

    public String[] infoGeter(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String json =  response.body().string();
        String[] temp = new String[2];
        temp[0]=json.substring(json.indexOf("text"),json.indexOf("createdAt")).replaceAll("\"","").replaceAll("text:","");
        temp[1]= json.substring(json.indexOf("createdAt"),json.indexOf("isReply")).replaceAll("\"","").replace("createdAt:","").replaceAll(",","");
        client.connectionPool().evictAll();
        return temp;
    }


    public boolean infodeterminer(String infor) {
        String info = infor.toLowerCase(Locale.ROOT);
        if (info.contains("transfers") && info.contains("available")) {
            return true;
        }
        return false;
    }

    public String[] infohandler(String info) {
        String c = "";
        if (info.contains("Transfers")) {
            c = info.replace("Transfers", "transfers");
        } else {
            c = info;
        }
        c = c.substring(c.indexOf("transfers"), c.indexOf("available"));

        if (info.contains("amp;")) {
            c = c.replace("amp;", "");
        }
        if (info.contains("and")) {
            c = c.replace("and", "&");
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < c.length()) {
            char chr = c.charAt(i);
            if (Character.isUpperCase(chr) || chr == ',' || chr == '&'||Character.isDigit(chr)) {
                sb.append(chr);
            }
            i++;
        }
        String[] coin = sb.toString().split(",|&");
        for (int j = 0; j < coin.length; j++) {
            coin[j] += "USDT";
        }
        return coin;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());
        WebListener ti = new WebListener();
        System.out.println(ti.infoGeter("http://3.112.41.245:8024/api/query?secretKey=X5y4zVJ3uJvBahAdrztXdBVy7xVJSt7m")[0]);
    }
}
