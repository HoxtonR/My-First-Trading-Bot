package hoxtonr.frame.HuobiFrame.Trade;

import com.huobi.client.TradeClient;
import com.huobi.client.req.trade.CreateOrderRequest;
import com.huobi.constant.HuobiOptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HuobiSpotTradeImp {
    private String apikey;
    private String secretkey;
    private Long accountId;
    public HuobiSpotTradeImp(String apikey, String secretkey, Long accountId) {
        this.apikey = apikey;
        this.secretkey = secretkey;
        this.accountId = accountId;
    }

    public void spotBuy(String cointype, BigDecimal amount, BigDecimal price) {
                TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                        .apiKey(apikey)
                        .secretKey(secretkey)
                        .build());
                CreateOrderRequest buyLimitRequest = CreateOrderRequest.spotBuyLimit(accountId, cointype,price, amount);
                Long buyLimitId = tradeService.createOrder(buyLimitRequest);
                System.out.println("create buy-limit order:" + buyLimitId);
    }

    public Long spotSell(String cointype, BigDecimal amount, BigDecimal price) {
                TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                        .apiKey(apikey)
                        .secretKey(secretkey)
                        .build());
                CreateOrderRequest sellLimitRequest = CreateOrderRequest.spotSellLimit(accountId, cointype, price, amount);
                return tradeService.createOrder(sellLimitRequest);

    }

    public void spotMarketBuy(String cointype, BigDecimal amount) {
                TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                        .apiKey(apikey)
                        .secretKey(secretkey)
                        .build());
                CreateOrderRequest buyMarketRequest = CreateOrderRequest.spotBuyMarket(accountId, cointype, amount);
                Long buyMarketId = tradeService.createOrder(buyMarketRequest);
                System.out.println("create buy-market order:" + buyMarketId);

    }

    public void spotMarketSELL(String cointype, BigDecimal amount) {
                TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                        .apiKey(apikey)
                        .secretKey(secretkey)
                        .build());
                CreateOrderRequest sellMarketRequest = CreateOrderRequest.spotSellMarket(accountId, cointype, amount);
                Long sellMarketId = tradeService.createOrder(sellMarketRequest);
                System.out.println("create sell-market order:" + sellMarketId);
    }
    public static void main(String[] args){
        System.out.println(LocalDateTime.now());
        HuobiSpotTradeImp trade = new HuobiSpotTradeImp("8494ebce-dbuqg6hkte-10e89102-6ce6e", "b0413b52-b7e6304b-a3b593b6-c0641", 16810182L);
        trade.spotBuy("btcusdt",BigDecimal.valueOf(0.001),BigDecimal.valueOf(40000));
        System.out.println(LocalDateTime.now());
    }
}
