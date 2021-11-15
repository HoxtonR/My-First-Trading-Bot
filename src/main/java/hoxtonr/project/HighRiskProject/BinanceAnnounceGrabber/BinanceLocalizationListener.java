package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;

public class BinanceLocalizationListener implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        BinanceLocalization l = new BinanceLocalization();
        System.out.println("币安监听已启动！");
        OkHttpClient client = new OkHttpClient();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("价格监听已启动！");
                while (true) {
                    try {
                        l.priceLocalization(client);
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("钱包监听已启动！");
                while (true) {
                    try {
                        l.balanceLocalization();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("交易规则监听已启动！");
                while (true) {
                    try {
                        l.tradeRuleLocalization();
                        Thread.sleep(300000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        BinanceLocalizationListener li = new BinanceLocalizationListener();
        Thread t = new Thread(li);
        t.start();
    }
}
