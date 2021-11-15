package hoxtonr.project.LowRiskProject.Arbitrage.Market;

import hoxtonr.frame.BinanceFrame.market.BinanceSwapFundingRate;
import hoxtonr.frame.BinanceFrame.market.BinanceSwapFundingRateImp;
import hoxtonr.frame.BinanceFrame.market.model.NextFundingRate;
import hoxtonr.frame.FTXFrame.Market.FutureMarket.FTXFundingRate;

import java.io.IOException;
import java.util.List;

public class CoinChargeFee {
    public String FundingRateChosen() throws IOException {
        BinanceSwapFundingRate rate = new BinanceSwapFundingRateImp();
        FTXFundingRate ftxrate = new FTXFundingRate();
        List<NextFundingRate> BinanceFunding = rate.BinanceNextFundingRate();
        for(int i = 0; i<BinanceFunding.size();i++) {
            NextFundingRate r = BinanceFunding.get(BinanceFunding.size()-1-i);
            if(r.getSymbol().contains("USDT")){
                String FTXCoin = r.getSymbol().replace("USDT","")+"-PERP";
                if(ftxrate.FTXNextFundingRate(FTXCoin) != null){
                    return r.getSymbol();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        CoinChargeFee fee = new CoinChargeFee();
        System.out.println(fee.FundingRateChosen());
    }
}
