package hoxtonr.frame.FTXFrame.WebConnection;

import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

public class WebConnectFactory {
    private final String ConnectionUrl = "https://ftx.com";
    private final MediaType JSON_TYPE = MediaType.parse("application/json");
    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

    Long gmtNow() {
        return now.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public String PostRequest(HashMap<String, String> InfoHash) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(ConnectionUrl + InfoHash.get("EndPoint"))
                .addHeader("FTX-KEY", InfoHash.get("ApiKey"))
                .addHeader("FTX-SIGN", InfoHash.get("Signature"))
                .addHeader("FTX-TS", InfoHash.get("Time"))
                .post(RequestBody.create(JSON_TYPE, InfoHash.get("RequestBody")))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                String c = response.body().string();
                response.close();
                okHttpClient.connectionPool().evictAll();
                return c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            okHttpClient.connectionPool().evictAll();
        }
        return null;
    }

    public String GetRequest(HashMap<String, String> InfoHash) {
        System.out.println(InfoHash.get("ApiKey"));
        System.out.println(InfoHash.get("EndPoint"));
        System.out.println(InfoHash.get("Signature"));
        System.out.println(gmtNow());
        Request request = new Request.Builder()
                .url(ConnectionUrl + InfoHash.get("EndPoint"))
                .addHeader("FTX-KEY", InfoHash.get("ApiKey"))
                .addHeader("FTX-TS", InfoHash.get("Time"))
                .addHeader("FTX-SIGN", InfoHash.get("Signature"))
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
