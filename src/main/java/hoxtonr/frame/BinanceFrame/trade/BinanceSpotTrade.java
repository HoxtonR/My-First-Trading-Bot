package hoxtonr.frame.BinanceFrame.trade;

import com.binance.api.client.domain.account.NewOrderResponse;

public interface BinanceSpotTrade{
    public NewOrderResponse BinanceSpotLimitBuy(String cointype, String price, String amount);
    public NewOrderResponse BinanceSpotLimitSell(String cointype, String price, String amount);
    public NewOrderResponse BinanceSpotMarketBuy(String cointype, String amount);
    public NewOrderResponse BinanceSpotMarketSell(String cointype, String amount);
}
