package hoxtonr.frame.BinanceFrame.trade;

import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.LocalDateTime;

public class BinanceSpotTradeXMarket {
    private String apikey;
    private String secretkey;
    private static final MediaType JSON_TYPE = MediaType.parse("application/json");
    public BinanceSpotTradeXMarket(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    public String execCurl(String[] cmds) {
        try {
            System.out.println(LocalDateTime.now());
            ProcessBuilder process = new ProcessBuilder(cmds);
            System.out.println(LocalDateTime.now());
            process.start();
            System.out.println(LocalDateTime.now());
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return "success!";
    }
    public  String HMACSHA256(String data, String key) throws Exception {

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
    public String[] RequestBuilder(String cointype, String side, String quantity) throws Exception {
        String api = "\"X-MBX-APIKEY:"+ apikey +"\"";
        String url = "https://api.binance.com/api/v3/order";
        long time = System.currentTimeMillis();
        String requestbody = "?"+"symbol="+cointype+"&side="+side+"&type=MARKET"+"&quantity="+quantity+"&recvWindow=5000&timestamp="+time;
        String signature = HMACSHA256(requestbody.replace("?",""),secretkey);
        requestbody += "&signature="+signature;
        String[] c = new String[]{"curl","-H",api,"-XPOST","\""+url+requestbody+"\""};
        return c;
    }
    public void BinanceSpotLimitBuyXMarket(String cointype, String quantity) throws Exception {
        String[] temp = RequestBuilder(cointype,"BUY",quantity);
        System.out.println(execCurl(temp));
    }
    public void BinanceSpotAlphaLimitBuyXMarket(String[] Request) throws Exception {
        System.out.println(execCurl(Request));
    }
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());
        BinanceSpotTradeXMarket trade = new BinanceSpotTradeXMarket("2H7ZNKLcYfVCDOEgwjt7hL9c3vti9z6UnlIm400B0kj3Wn4IUOsfdYYoW5Z2AVmr","siHxM5KwtnPvQLjyIXP8NsPsioLzajp2cUhJknAgJrgkiulZeQTI2rBCkmw4skrT");
        for(int i = 0;i<10000;i++){

        }
        System.out.println("开始"+LocalDateTime.now());
         trade.execCurl(new String[]{"curl","www.google.ca"});
        //trade.okHttpPost("https://www.google.ca","asdad");
    }

}
