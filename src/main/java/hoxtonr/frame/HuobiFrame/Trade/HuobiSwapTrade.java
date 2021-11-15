package hoxtonr.frame.HuobiFrame.Trade;

import com.huobi.api.response.trade.SwapOrderResponse;

import java.math.BigDecimal;

public interface HuobiSwapTrade {
    public void HuobiSwapBuyLong(String cointype, BigDecimal price, Long volume, int leverage);

    public SwapOrderResponse.DataBean HuobiSwapSellLong(String cointype, BigDecimal price, Long volume, int leverage);

    public void HuobiSwapBuyShort(String cointype, BigDecimal price, Long volume, int leverage);

    public void HuobiSwapSellShort(String cointype, BigDecimal price, Long volume, int leverage);

    public void HuoBiSwapBuyLongMarket(String cointype, Long volume, int leverage);

    public void HuobiSwapSellLongMarket(String cointype, Long volume, int leverage);

    public void HuobiSwapBuyShortMarket(String cointype, Long volume, int leverage);

    public void HUobiSwapSellShortMarket(String cointype, Long volume, int leverage);
}
