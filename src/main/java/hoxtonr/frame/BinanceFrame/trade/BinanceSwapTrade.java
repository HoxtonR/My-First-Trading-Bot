package hoxtonr.frame.BinanceFrame.trade;

import com.client.model.trade.Order;

public interface BinanceSwapTrade {
    public Order BinanceSwapLimitBuyOpen(String cointype, String Price, String amount, int leverage);
    public Order BinanceSwapLimitSellClose(String cointype, String Price,String amount, int leverage);
    public Order BinanceSwapMarketBuyOpen(String cointype,String amount, int leverage);
    public Order BinanceSwapMarketSellClose(String cointype,String amount, int leverage);
    public Order BinanceSwapLimitSellOpen(String cointype,String Price,String amount, int leverage);
    public Order BinanceSwapLimitBuyClose(String cointype, String Price,String amount,int leverage);
    public Order BinanceSwapMarketSellOpen(String cointype, String amount, int leverage);
    public Order BinanceSwapMarketBuyClose(String cointype, String amount,int leverage);
}
