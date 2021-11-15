package hoxtonr.frame.BinanceFrame.Account;

import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.trade.AccountBalance;
import com.client.model.trade.Position;
import com.client.model.trade.PositionRisk;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.ArbitrageAccount;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class BinanceSwapAccountImp implements BinanceSwapAccount {
    private String Username;
    private String apikey;
    private String secretkey;

    public BinanceSwapAccountImp(String Username, String apikey, String secretkey) {
        this.Username = Username;
        this.apikey = apikey;
        this.secretkey = secretkey;
    }

    @Override
    public String BinanceSwapBalance(String cointype) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        for (Position p : syncRequestClient.getAccountInformation().getPositions()) {
            if (p.getSymbol().equals(cointype) && p.getPositionSide().equals("LONG")) {
                return p.getPositionAmt();
            }
        }
        return null;
    }
    @Override
    public String BinanceSwapShortBalance(String cointype) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        for (Position p : syncRequestClient.getAccountInformation().getPositions()) {
            if (p.getSymbol().equals(cointype) && p.getPositionSide().equals("SHORT")) {
                return p.getPositionAmt();
            }
        }
        return null;
    }

    @Override
    public String BinanceSwapUsdt() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey, options);
        for (AccountBalance c : syncRequestClient.getBalance()) {
            if (c.getAsset().equals("USDT")) {
                return String.valueOf(c.getBalance());
            }
        }
        return null;
    }
    @Override
    public String getSwapTransferBalance() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        for (AccountBalance c : syncRequestClient.getBalance()) {
            if (c.getAsset().equals("USDT")) {
                return String.valueOf(c.getWithdrawAvailable().setScale(2, RoundingMode.DOWN));
            }
        }
        return null;
    }

    @Override
    public void getPositions() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        List<PositionRisk> risk = syncRequestClient.getPositionRisk();
        System.out.println(risk);
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        BinanceSwapAccount b = new BinanceSwapAccountImp("HoxtonR", ArbitrageAccount.getApiKey(),ArbitrageAccount.getSecretKey());
        System.out.println(b.BinanceSwapShortBalance("DOGEUSDT"));
        System.out.println(LocalDateTime.now());
    }

}
