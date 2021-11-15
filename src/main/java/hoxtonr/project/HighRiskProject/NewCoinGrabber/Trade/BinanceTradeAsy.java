package hoxtonr.project.HighRiskProject.NewCoinGrabber.Trade;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.OrderRequest;
import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccount;
import hoxtonr.frame.BinanceFrame.Account.BinanceSpotAccountImp;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.BinanceApiKey;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BinanceTradeAsy implements Runnable {
    private String[] cointypes;
    private String apikey;
    private String secretkey;
    private String balance;

    public BinanceTradeAsy(String[] cointypes, String username) {
        this.cointypes = cointypes;
        this.apikey = BinanceApiKey.getApikey();
        this.secretkey = BinanceApiKey.getSecretkey();
        BinanceSpotAccount account = new BinanceSpotAccountImp(BinanceApiKey.getApikey(), BinanceApiKey.getSecretkey());
        this.balance = String.valueOf(BigDecimal.valueOf(Double.parseDouble(account.BinanceSpotUsdt())).setScale(2,RoundingMode.DOWN));
    }

    public List<com.binance.api.client.domain.account.Order> getSpotorder(String cointype) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        List<com.binance.api.client.domain.account.Order> openOrders = client.getOpenOrders(new OrderRequest(cointype));
        return openOrders;
    }
    public void cancelSpotOrder(String cointype, Long orderid) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        try {
            CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest(cointype, orderid));
            System.out.println(cancelOrderResponse);
        } catch (com.binance.api.client.exception.BinanceApiException e) {
            System.out.println(e.getError().getMsg());
        }
    }
    public static void main(String[] args) {

    }

    @Override
    public void run() {

    }
}
