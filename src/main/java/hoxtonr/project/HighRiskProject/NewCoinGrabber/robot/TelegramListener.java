package hoxtonr.project.HighRiskProject.NewCoinGrabber.robot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.BinanceApiKey;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.HuobiApiKey;

import java.util.List;

public class TelegramListener implements Runnable {
    TelegramBot bot;

    public TelegramListener() {
        bot = new TelegramBot("1843886143:AAEb9nmx-OfN6H57WkqkKipFhvS6pny18-g");
    }

    @Override
    public void run() {
        bot.setUpdatesListener(new UpdatesListener() {
            TelegramRobot tr = new TelegramRobot();
            QuickFunctions function = new QuickFunctions("Vida", HuobiApiKey.getApi_key(),HuobiApiKey.getSecret_key(),HuobiApiKey.getAccountId(), BinanceApiKey.getApikey(),BinanceApiKey.getSecretkey());
            String c = "";
            @Override
            public int process(List<Update> updates) {
                try {
                    if (updates.get(0).message().text() != null) {
                        if (!updates.get(0).message().text().equals(c)) {
                            System.out.println(updates.get(0).message().text());
                            c = updates.get(0).message().text().replace("@buynewlist_bot","");
                            if (c.equals("/sell")) {
                                function.BinanceSwapCloseAllPosition();
                                function.BianceSpotSellAll();
                                function.HuobiSwapCloseAllPosition();
                                function.HuobiSpotSellAll();
                                tr.sendmessage("【总控】机器人已将所有合约与现货平仓");
                            }
                            if (c.equals("/collect")) {
                                function.HuobiTransferUsdtFutureToSpot("-1");
                                function.Binancetransferall();
                                tr.sendmessage("【总控】机器人已将所有合约资金划转到现货");
                            }
                        }
                    }
                } catch (NullPointerException ignore) {

                } catch (Exception e) {
                    e.printStackTrace();
                    tr.sendmessage("出错了！错误原因为: " + e.getMessage());
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
    public static void main(String[] args){
        TelegramListener L = new TelegramListener();
        Thread t = new Thread(L);
        t.start();
    }
}
