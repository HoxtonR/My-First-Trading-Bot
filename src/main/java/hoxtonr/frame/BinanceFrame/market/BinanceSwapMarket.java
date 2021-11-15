package hoxtonr.frame.BinanceFrame.market;

import com.client.model.market.FundingRate;

import java.util.List;

public interface BinanceSwapMarket {
    public String BinanceSwapMarketPrice(String cointype);
    public List<FundingRate> BinanceFundingRate(String apikey, String secretkey);
}
