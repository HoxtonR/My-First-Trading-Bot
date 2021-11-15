package hoxtonr.frame.BinanceFrame.trade;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.NewOrderResponseType;

import static com.binance.api.client.domain.account.NewOrder.*;

public class BinanceSpotTradeImp implements BinanceSpotTrade{
    private String apikey;
    private String secretkey;
    public BinanceSpotTradeImp(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    @Override
    public NewOrderResponse BinanceSpotLimitBuy(String cointype, String price, String amount) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        return client.newOrder(limitBuy(cointype, TimeInForce.GTC,amount,price).newOrderRespType(NewOrderResponseType.FULL));
    }

    @Override
    public NewOrderResponse BinanceSpotLimitSell(String cointype, String price, String amount) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        return  client.newOrder(limitSell(cointype,TimeInForce.GTC,amount,price).newOrderRespType(NewOrderResponseType.FULL));
    }

    @Override
    public NewOrderResponse BinanceSpotMarketBuy(String cointype, String amount) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        return client.newOrder(marketBuy(cointype,amount).newOrderRespType(NewOrderResponseType.FULL));
    }

    @Override
    public NewOrderResponse BinanceSpotMarketSell(String cointype, String amount) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apikey, secretkey);
        BinanceApiRestClient client = factory.newRestClient();
        return client.newOrder(marketSell(cointype,amount).newOrderRespType(NewOrderResponseType.FULL));
    }
    public static void main(String[] args){
        BinanceSpotTradeImp trade = new BinanceSpotTradeImp("2H7ZNKLcYfVCDOEgwjt7hL9c3vti9z6UnlIm400B0kj3Wn4IUOsfdYYoW5Z2AVmr","siHxM5KwtnPvQLjyIXP8NsPsioLzajp2cUhJknAgJrgkiulZeQTI2rBCkmw4skrT");
        trade.BinanceSpotLimitBuy("BTCUSDT","45000","0.001");
    }
}
