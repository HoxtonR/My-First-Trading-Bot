package hoxtonr.frame.BinanceFrame.trade;

import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.enums.*;
import com.client.model.trade.Order;

public class BinanceSwapTradeImp implements BinanceSwapTrade{
    private String apikey;
    private String secretkey;
    public BinanceSwapTradeImp(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    @Override
    public Order BinanceSwapLimitBuyOpen(String cointype, String Price, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        syncRequestClient.changeInitialLeverage(cointype,leverage);
        return syncRequestClient.postOrder(cointype, OrderSide.BUY, PositionSide.LONG, OrderType.LIMIT, TimeInForce.GTC,
               amount,Price, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapLimitSellClose(String cointype, String Price, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);

        return  syncRequestClient.postOrder(cointype, OrderSide.SELL, PositionSide.LONG, OrderType.LIMIT, TimeInForce.GTC,
                amount,Price, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapMarketBuyOpen(String cointype, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        syncRequestClient.changeInitialLeverage(cointype,leverage);
        return syncRequestClient.postOrder(cointype, OrderSide.BUY, PositionSide.LONG, OrderType.MARKET, null,
                amount, null, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapMarketSellClose(String cointype, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);

        return syncRequestClient.postOrder(cointype, OrderSide.SELL, PositionSide.LONG, OrderType.MARKET, null,
                amount, null, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapLimitSellOpen(String cointype, String Price, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        syncRequestClient.changeInitialLeverage(cointype,leverage);
        return  syncRequestClient.postOrder(cointype, OrderSide.SELL, PositionSide.SHORT, OrderType.LIMIT, TimeInForce.GTC,
                amount,Price, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapLimitBuyClose(String cointype, String Price, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
         return syncRequestClient.postOrder(cointype, OrderSide.BUY, PositionSide.SHORT, OrderType.LIMIT, TimeInForce.GTC,
                amount,Price, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapMarketSellOpen(String cointype, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        syncRequestClient.changeInitialLeverage(cointype,leverage);
        return syncRequestClient.postOrder(cointype, OrderSide.SELL, PositionSide.SHORT, OrderType.MARKET, null,
                amount, null, null, null, null, null, NewOrderRespType.RESULT);
    }

    @Override
    public Order BinanceSwapMarketBuyClose(String cointype, String amount, int leverage) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        return syncRequestClient.postOrder(cointype, OrderSide.BUY, PositionSide.SHORT, OrderType.MARKET, null,
                amount, null, null, null, null, null, NewOrderRespType.RESULT);
    }

}
