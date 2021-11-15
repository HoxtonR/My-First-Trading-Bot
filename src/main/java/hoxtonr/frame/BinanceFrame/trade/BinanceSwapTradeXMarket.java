package hoxtonr.frame.BinanceFrame.trade;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class BinanceSwapTradeXMarket {
    private String apikey;
    private String secretkey;

    public BinanceSwapTradeXMarket(String apikey, String secretkey) {
        this.apikey = apikey;
        this.secretkey = secretkey;
    }

    public String execCurl(String[] cmds) {
        System.out.println(LocalDateTime.now());
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            System.out.println(LocalDateTime.now());
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            System.out.println(LocalDateTime.now());
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return "sb";
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
    private String[] RequestBuilder(String cointype, String side, String positionSide, String quantity) throws Exception {
        String api = "\"X-MBX-APIKEY:" + apikey + "\"";
        String url = "https://fapi.binance.com/fapi/v1/order";
        long time = System.currentTimeMillis();
        String requestbody = "?" + "symbol=" + cointype + "&side=" + side + "&positionSide=" + positionSide + "&type=MARKET" + "&quantity=" + quantity + "&recvWindow=5000&timestamp=" + time;
        String signature = HMACSHA256(requestbody.replace("?", ""), secretkey);
        requestbody += "&signature=" + signature;
        String[] c = new String[]{"curl", "-H", api, "-XPOST", "\"" + url + requestbody + "\""};
        return c;
    }

    public void BinanceSwapLimitBuyXMarket(String cointype, String quantity) throws Exception {
        new Thread(() -> {
            String[] temp = new String[0];
            try {
                temp = RequestBuilder(cointype, "BUY", "LONG", quantity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(execCurl(temp));
        }).start();
    }

    public static void main(String[] args) throws Exception {
        BinanceSwapTradeXMarket trade = new BinanceSwapTradeXMarket("2H7ZNKLcYfVCDOEgwjt7hL9c3vti9z6UnlIm400B0kj3Wn4IUOsfdYYoW5Z2AVmr","siHxM5KwtnPvQLjyIXP8NsPsioLzajp2cUhJknAgJrgkiulZeQTI2rBCkmw4skrT");
        trade.BinanceSwapLimitBuyXMarket("BTCUSDT","0.001");
    }
}
