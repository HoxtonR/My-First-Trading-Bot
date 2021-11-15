package hoxtonr.frame.BinanceFrame.trade;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class BinanceTransferX {
    private String apikey;
    private String secretkey;
    public BinanceTransferX(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    private  String execCurl(String[] cmds) {
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
    private  String HMACSHA256(String data, String key) throws Exception {

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
    private String[] RequestBuilder(String type, String asset, String amount) throws Exception {
        String head = "curl";
        String api = "\"X-MBX-APIKEY:"+ apikey +"\"";
        String url = "https://api.binance.com/sapi/v1/asset/transfer";
        long time = System.currentTimeMillis();
        String requestbody = "?"+"&type="+type+"&asset="+asset+"&amount="+amount+"&recvWindow=5000&timestamp="+time;
        String signature = HMACSHA256(requestbody.replace("?",""),secretkey);
        requestbody += "&signature="+signature;
        String[] c = new String[]{"curl","-H",api,"-XPOST","\""+url+requestbody+"\""};
        return c;
    }
    public void BinanceTransferX_SpotToUsdtFuture(String asset, String amount) throws Exception {
        String[] c = RequestBuilder("MAIN_UMFUTURE",asset,amount);
        System.out.println(execCurl(c));
    }
    public void BinanceTransferX_UsdtFuturetoSpot(String asset, String amount) throws Exception {
        String[] c = RequestBuilder("UMFUTURE_MAIN",asset,amount);
        System.out.println(execCurl(c));
    }
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());
        BinanceTransferX x = new BinanceTransferX("2H7ZNKLcYfVCDOEgwjt7hL9c3vti9z6UnlIm400B0kj3Wn4IUOsfdYYoW5Z2AVmr","siHxM5KwtnPvQLjyIXP8NsPsioLzajp2cUhJknAgJrgkiulZeQTI2rBCkmw4skrT");
        x.BinanceTransferX_UsdtFuturetoSpot("USDT","40");
        System.out.println(LocalDateTime.now());
    }
}
