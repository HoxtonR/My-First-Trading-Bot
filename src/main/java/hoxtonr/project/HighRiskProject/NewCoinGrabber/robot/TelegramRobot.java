package hoxtonr.project.HighRiskProject.NewCoinGrabber.robot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class TelegramRobot {
    TelegramBot bot;

    public TelegramRobot() {
        bot = new  TelegramBot("1843886143:AAEb9nmx-OfN6H57WkqkKipFhvS6pny18-g");
    }

    public void sendmessage(String sendmessage) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SendMessage request = new SendMessage(-1001229368286L, sendmessage);
                SendResponse sendResponse = bot.execute(request);
                boolean ok = sendResponse.isOk();
                Message message = sendResponse.message();
            }
        }).start();
    }

    public void getupdate() {
        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();
        for (Update u : updates) {
            if (u.message() != null) {
                System.out.println(u.message().text());
            }
        }
    }

}

