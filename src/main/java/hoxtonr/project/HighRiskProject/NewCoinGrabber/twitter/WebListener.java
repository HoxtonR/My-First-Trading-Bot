package hoxtonr.project.HighRiskProject.NewCoinGrabber.twitter;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class WebListener {

    public String[] infoGeter(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String RequestString = "{\"offset\":0,\"limit\":1}";
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json,RequestString);
        Request request = new Request.Builder()
                .header("Content-Type","application/json")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String temp = response.body().string();
        String content = temp.substring(temp.indexOf("title"), temp.indexOf("pubDate")).replaceAll("\"", "").replace("title:", "");
        String pubDate = temp.substring(temp.indexOf("pubDate"), temp.indexOf("}")).replaceAll("pubDate\":", "");
        client.connectionPool().evictAll();
        return new String[]{content, pubDate};
    }


    public boolean infodeterminer(String info) {
        String infor = info.toLowerCase(Locale.ROOT);
        if (info.contains("launching")&&info.contains("(")&&info.contains(")")) {
            return true;
        }
        return false;
    }

    public String[] infohandler(String info) {
        ArrayList<String> word = new ArrayList<String>();
        int m = 0, n = 0;
        int count = 0;
        for (int i = 0; i < info.length(); i++) {
            if (info.charAt(i) == '(') {
                if (count == 0) {
                    m = i;
                }
                count++;
            }
            if (info.charAt(i) == ')') {
                count--;
                if (count == 0) {
                    n = i;
                    word.add(info.substring(m+1, n));
                }
            }
        }
        for (int i = 0 ; i<word.size();i++) {
            word.set(i,word.get(i)+"USDT");
        }
        return word.toArray(new String[0]);
    }

}
