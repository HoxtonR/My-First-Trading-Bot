package hoxtonr.frame.BinanceFrame.market;

import com.client.model.market.FundingRate;
import hoxtonr.frame.BinanceFrame.market.model.NextFundingRate;

import java.io.IOException;
import java.util.List;

public interface BinanceSwapFundingRate {
    public List<FundingRate> BinanceLastFundingRate();
    public List<NextFundingRate> BinanceNextFundingRate() throws IOException;
}
