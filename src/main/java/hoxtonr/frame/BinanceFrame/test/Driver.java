package hoxtonr.frame.BinanceFrame.test;


import hoxtonr.frame.BinanceFrame.market.BinanceSpotMarket;
import hoxtonr.frame.BinanceFrame.market.BinanceSpotMarketImp;
import hoxtonr.frame.BinanceFrame.trade.BinanceSpotTrade;
import hoxtonr.frame.BinanceFrame.trade.BinanceSpotTradeImp;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Driver implements Runnable {
    @Override
    public void run() {
        twitter_info info = new twitter_info();
        String c = info.infoGeter();
        while (true) {
            try {
                String mess = info.infoGeter();
                if (c.equals(mess)) {
                    continue;
                }
                if (info.infodeterminer(mess)) {
                    BinanceSpotTrade trade = new BinanceSpotTradeImp("2H7ZNKLcYfVCDOEgwjt7hL9c3vti9z6UnlIm400B0kj3Wn4IUOsfdYYoW5Z2AVmr", "siHxM5KwtnPvQLjyIXP8NsPsioLzajp2cUhJknAgJrgkiulZeQTI2rBCkmw4skrT");
                    BinanceSpotMarket market = new BinanceSpotMarketImp();
                }
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public String execCurl(String[] cmds) {
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


    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());

    }
}
