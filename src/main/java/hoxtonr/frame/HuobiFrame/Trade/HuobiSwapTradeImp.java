package hoxtonr.frame.HuobiFrame.Trade;

import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import com.huobi.api.request.trade.SwapOrderRequest;
import com.huobi.api.response.trade.SwapOrderResponse;
import com.huobi.api.service.trade.TradeAPIServiceImpl;

import java.math.BigDecimal;

public class HuobiSwapTradeImp implements HuobiSwapTrade{
    private String apikey;
    private String secretkey;
    public HuobiSwapTradeImp(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }

    @Override
    public void HuobiSwapBuyLong(String cointype, BigDecimal price, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .price(price)
                    .direction(DirectionEnum.BUY)
                    .offset(OffsetEnum.OPEN)
                    .leverRate(leverage)
                    .orderPriceType("limit")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }
    @Override
    public SwapOrderResponse.DataBean HuobiSwapSellLong(String cointype, BigDecimal price, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .price(price)
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.CLOSE)
                    .leverRate(leverage)
                    .orderPriceType("limit")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
        return response.getData();
    }

    @Override
    public void HuobiSwapBuyShort(String cointype, BigDecimal price, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .price(price)
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.OPEN)
                    .leverRate(leverage)
                    .orderPriceType("limit")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }

    @Override
    public void HuobiSwapSellShort(String cointype, BigDecimal price, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .price(price)
                    .direction(DirectionEnum.BUY)
                    .offset(OffsetEnum.CLOSE)
                    .leverRate(leverage)
                    .orderPriceType("limit")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }

    @Override
    public void HuoBiSwapBuyLongMarket(String cointype, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .direction(DirectionEnum.BUY)
                    .offset(OffsetEnum.OPEN)
                    .leverRate(leverage)
                    .orderPriceType("optimal_20")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }

    @Override
    public void HuobiSwapSellLongMarket(String cointype, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.CLOSE)
                    .leverRate(leverage)
                    .orderPriceType("optimal_20")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }

    @Override
    public void HuobiSwapBuyShortMarket(String cointype, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.OPEN)
                    .leverRate(leverage)
                    .orderPriceType("optimal_20")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }

    @Override
    public void HUobiSwapSellShortMarket(String cointype, Long volume, int leverage) {
            TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(apikey, secretkey);
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode(cointype)
                    .volume(volume)
                    .direction(DirectionEnum.BUY)
                    .offset(OffsetEnum.CLOSE)
                    .leverRate(leverage)
                    .orderPriceType("optimal_20")
                    .build();
            SwapOrderResponse response =
                    huobiAPIService.swapOrderRequest(request);
    }
}

