package hoxtonr.frame.HuobiFrame.Trade;

import com.huobi.api.crossrequest.trade.SwapCrossOrderRequest;
import com.huobi.api.crossresponse.trade.SwapCrossOrderResponse;
import com.huobi.api.crossservice.crosstrade.CrossTradeAPIService;
import com.huobi.api.crossservice.crosstrade.CrossTradeAPIServiceImpl;
import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;

import java.math.BigDecimal;

public class HuobiSwapCrossTrade {
    private String apikey;
    private String secretkey;
    public HuobiSwapCrossTrade(String apikey, String secretkey){
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    public Long HuobiSwapCrossSellLongLimit(String cointype, BigDecimal price, Long volume, int leverage){
        CrossTradeAPIServiceImpl huobiAPIService = new CrossTradeAPIServiceImpl(apikey, secretkey);
        SwapCrossOrderRequest request = SwapCrossOrderRequest.builder()
                .contractCode(cointype)
                .volume(volume)
                .price(price)
                .direction(DirectionEnum.SELL)
                .offset(OffsetEnum.CLOSE)
                .leverRate(leverage)
                .orderPriceType("limit")
                .build();
        SwapCrossOrderResponse response =
                huobiAPIService.swapCrossOrderRequest(request);
        return response.getData().getOrderId();
    }
    public Long HuobiSwapCrossSellLongMarket(String cointype, Long volume, int leverage) {
        CrossTradeAPIService trade = new CrossTradeAPIServiceImpl(apikey,secretkey);
        SwapCrossOrderRequest request =  SwapCrossOrderRequest.builder()
                .contractCode(cointype)
                .volume(volume)
                .direction(DirectionEnum.SELL)
                .offset(OffsetEnum.CLOSE)
                .leverRate(leverage)
                .orderPriceType("optimal_20")
                .build();
        SwapCrossOrderResponse response = trade.swapCrossOrderRequest(request);
        return response.getData().getOrderId();

    }

}
