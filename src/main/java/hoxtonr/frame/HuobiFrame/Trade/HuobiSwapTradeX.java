package hoxtonr.frame.HuobiFrame.Trade;

import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class HuobiSwapTradeX {
    private String apikey;
    private String secretkey;
    private Long accountId;
    static final ZoneId ZONE_GMT = ZoneId.of("Z");
    static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final MediaType JSON_TYPE = MediaType.parse("application/json");
    long epochNow() {
        return Instant.now().getEpochSecond();
    }

    String gmtNow() {
        return Instant.ofEpochSecond(epochNow()).atZone(ZONE_GMT).format(DT_FORMAT);
    }


    public HuobiSwapTradeX(String apikey, String secretkey, Long accountId) {
        this.apikey = apikey;
        this.secretkey = secretkey;
        this.accountId = accountId;
    }

    private String execCurl(String[] cmds) {
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }

    private String HMACSHA256(String data, String key) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (byte item : array) {

            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));

        }

        return sb.toString().toUpperCase();

    }
    public String getURLEncoder(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[] RequestBuilder(String cointype, String volume, String price, String direction, String offset, int leverage) throws Exception {
        String head = "POST\n";
        String url = "api.hbdm.com\n";
        String url2 = "/linear-swap-api/v1/swap_cross_order\n";
        String time = gmtNow();
        String body = "AccessKeyId=" +apikey+"&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp="+ getURLEncoder(time);
        String temp = head + url + url2 + body;
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey = new SecretKeySpec(secretkey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        byte[] hash = hmacSha256.doFinal(temp.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.getEncoder().encodeToString(hash);
        StringBuilder build = new StringBuilder();
        build.append("&SignatureVersion=2&SignatureMethod=HmacSHA256&Timestamp=").append(getURLEncoder(time)).append("&AccessKeyId=").append(apikey);
        build.append("&Signature=").append(getURLEncoder(actualSign));
        String temp2 = "https://api.hbdm.com/linear-swap-api/v1/swap_cross_order?" + build.toString();
        StringBuilder request  = new StringBuilder();
        request.append("{").append("\"contract_code\":").append("\"").append(cointype).append("\"").append(",")
                .append("\"price\":").append("\"").append(price).append("\"").append(",")
                .append("\"volume\":").append("\"").append(volume).append("\"").append(",")
                .append("\"direction\":").append("\"").append(direction).append("\"").append(",")
                .append("\"offset\":").append("\"").append(offset).append("\"").append(",")
                .append("\"order_price_type\":").append("\"").append("limit").append("\"").append(",")
                .append("\"lever_rate\":").append("\"").append(leverage).append("\"").append("}");
        System.out.println(request.toString());
        String[] requestArray = new String[2];
        requestArray[0] = temp2;
        requestArray[1] = request.toString();
        return requestArray;
    }
    public String sendRequest(String url, String requestBody) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url(url)
                .post(RequestBody.create(JSON_TYPE, requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response re = okHttpClient.newCall(request).execute();
        return re.body().string();
    }

    public String HuobiSwapLimitBuyX(String cointype, String volume, String price, String direction, String offset, int leverage) throws Exception {
        String[] a = RequestBuilder(cointype,volume,price,direction,offset,leverage);
        return sendRequest(a[0],a[1]);

    }
    public static void main(String[] args) throws Exception {
        HuobiSwapTradeX trade = new HuobiSwapTradeX("8494ebce-dbuqg6hkte-10e89102-6ce6e", "b0413b52-b7e6304b-a3b593b6-c0641", 16810182L);
        HuobiTransferX transfer = new HuobiTransferX("8494ebce-dbuqg6hkte-10e89102-6ce6e", "b0413b52-b7e6304b-a3b593b6-c0641", 16810182L);
        transfer.HuobiTransferX_SpotToUsdtFuture("50","USDT");
        trade.HuobiSwapLimitBuyX("ETH-USDT","1","3100","BUY","OPEN",3);
    }
}
