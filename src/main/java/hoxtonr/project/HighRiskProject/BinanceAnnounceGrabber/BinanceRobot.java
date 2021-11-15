package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;


import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccount;
import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccountImp;
import hoxtonr.frame.BinanceFrame.trade.BinanceSpotTrade;
import hoxtonr.frame.BinanceFrame.trade.BinanceSpotTradeImp;
import hoxtonr.frame.BinanceFrame.trade.BinanceSpotTradeXMarket;
import lombok.SneakyThrows;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BinanceRobot implements Runnable {
    private int sellTime1 = 60000;
    private int sellTime2 = 30000;
    private int sellTime3 = 30000;
    private final String url = "http://54.150.5.108:3862/api/announcements";
    private HashMap<String, String[]> requestMap;
    private final String apikey;
    private final String secretkey;
    private final BinanceSpotAccount binanceSpotAccount;
    TelegramRobot robot;

    public BinanceRobot(String apikey, String secretkey) {
        this.apikey = apikey;
        this.secretkey = secretkey;
        binanceSpotAccount = new BinanceSpotAccountImp(apikey, secretkey);
        robot = new TelegramRobot();
    }

    @SneakyThrows
    @Override
    public void run() {
        BinanceWebListener listener = new BinanceWebListener();
        CloseableHttpClient client = HttpClients.custom()
                .evictExpiredConnections()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .build();
        requestMap = new HashMap<>();
        List<AnnounceModel> m = listener.infoList(client,url);
        double usdtbalance = doubleConverter(binanceSpotAccount.BinanceSpotUsdt());
        double busdbalance = doubleConverter(binanceSpotAccount.BinanceSpotBusd());
        ProcessBuilder process;
        process = new ProcessBuilder("cmd");
        process.start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    try {
                        requestMap = RequestHashMap();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("RequestMap生成失败，重新生成中！");
                    }
                }
            }
        }).start();
        while (true) {
            try {
                List<AnnounceModel> m2 = listener.infoList(client,url);
                if(modelDifference(m2, m).isEmpty()) {
                    Thread.sleep(10);
                    continue;
                }
                for(AnnounceModel mm : modelDifference(m2, m)) {
                    if (listener.infoDeterminer(mm.getTitle())) {
                        robot.sendmessage("检测到币安公告【" + mm.getTitle() + "】, 获取时间为:" + LocalDateTime.now());
                        for (String a : listener.infoHandler(mm.getTitle())) {
                            System.out.println(LocalDateTime.now());
                            process = new ProcessBuilder(requestMap.get(a));
                            System.out.println(LocalDateTime.now());
                            process.start();
                            System.out.println(LocalDateTime.now());
                            robot.sendmessage("【币安】程序买入现货" + a + "\n发起时间为: " + LocalDateTime.now());
                            sellByTime(a);
                            System.out.println("买入" + a);
                        }
                        Thread.sleep(sellTime1 + sellTime2 + sellTime3 + 5000);
                        double usdtbalance2 = doubleConverter(binanceSpotAccount.BinanceSpotUsdt());
                        double busdbalance2 = doubleConverter(binanceSpotAccount.BinanceSpotBusd());
                        robot.sendmessage("【币安】本次操作盈利为: " + ((usdtbalance2+busdbalance2) - (usdtbalance+busdbalance)));

                    }
                }
                Thread.sleep(10);
                m = m2;
            } catch (Exception e) {
                System.out.println("公告获取失败，重新获取中！");
            }
        }
    }

    //setget方法
    public int getSellTime1() {
        return sellTime1;
    }

    public void setSellTime1(int sellTime1) {
        this.sellTime1 = sellTime1;
    }

    public int getSellTime2() {
        return sellTime2;
    }

    public void setSellTime2(int sellTime2) {
        this.sellTime2 = sellTime2;
    }

    public int getSellTime3() {
        return sellTime3;
    }

    public void setSellTime3(int sellTime3) {
        this.sellTime3 = sellTime3;
    }

    //对比两个AnnounceModel的差集
    public List<AnnounceModel> modelDifference(List<AnnounceModel> originArray, List<AnnounceModel> newArray){
        newArray.removeAll(originArray);
        return newArray;
    }
    //生成Request的URL
    private HashMap<String, String[]> RequestHashMap() throws Exception {
        HashMap<String, String[]> requestMap = new HashMap<>();
        BinanceSpotTradeXMarket trade = new BinanceSpotTradeXMarket(BinanceAccount.getApiKey(), BinanceAccount.getSecretKey());
        BinanceLocalization localization = new BinanceLocalization();
        HashMap<String, Double> m = localization.priceHandler();
        double balance = Double.parseDouble(localization.balanceGeter());
        HashMap<String, Integer[]> ruleMap = localization.tradeRuleHandler();
        for (String key : m.keySet()) {
            if (ruleMap.containsKey(key)) {
                double amount = BigDecimal.valueOf(balance / m.get(key) * 0.85).setScale(ruleMap.get(key)[0], RoundingMode.DOWN).doubleValue();
                String[] request = trade.RequestBuilder(key, "BUY", String.valueOf(amount));
                requestMap.put(key, request);
            }
        }
        return requestMap;
    }
    //强转String为Double
    private double doubleConverter(String strVal) {
        return Double.parseDouble(strVal);
    }

    //多线程卖出策略
    private void sellByTime(String cointype) throws InterruptedException {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(sellTime1);
                BinanceSpotTrade trade = new BinanceSpotTradeImp(apikey, secretkey);
                BinanceLocalization li = new BinanceLocalization();
                HashMap<String, Integer[]> tradeRuleMap = li.tradeRuleHandler();
                Integer[] i = tradeRuleMap.get(cointype);
                double amount = BigDecimal.valueOf(doubleConverter(binanceSpotAccount.BinanceSpotBalance(cointype))/3).setScale(i[0], RoundingMode.DOWN).doubleValue();
                System.out.println(amount);
                trade.BinanceSpotMarketSell(cointype, String.valueOf(amount));
                robot.sendmessage("【币安】正在进行第一次卖出,金额为: "+amount + cointype);
                Thread.sleep(sellTime2);
                trade.BinanceSpotMarketSell(cointype, String.valueOf(amount));
                robot.sendmessage("【币安】正在进行第二次卖出,金额为: "+amount + cointype);
                Thread.sleep(sellTime3);
                trade.BinanceSpotMarketSell(cointype, String.valueOf(amount));
                robot.sendmessage("【币安】正在进行第三次卖出,金额为: "+amount + cointype);
            }
        }).start();
    }
    public static void main(String[] args) throws InterruptedException {
        BinanceRobot r = new BinanceRobot(BinanceAccount.getApiKey(),BinanceAccount.getSecretKey());
        BinanceWebListener l = new BinanceWebListener();
        CloseableHttpClient client = HttpClients.custom()
                .evictExpiredConnections()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .build();
        List<AnnounceModel> m1 = l.infoList(client,"http://54.150.5.108:3862/api/announcements");
        List<AnnounceModel> m2 = l.infoList(client,"http://54.150.5.108:3862/api/announcements");
        System.out.println(r.modelDifference(m1,m2));

    }
}
