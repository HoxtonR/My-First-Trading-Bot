package hoxtonr.frame.FTXFrame.FTXInfoListener;

import hoxtonr.frame.FTXFrame.Market.FutureMarket.FTXFutureMarket;

import java.io.FileWriter;
import java.io.IOException;

public class FTXFutureMarketListener{
    public void writeFutureMarket() throws IOException {

        FileWriter write = new FileWriter("FTXFutureMarket.json",false);
        FTXFutureMarket market = new FTXFutureMarket();
        write.write(market.getFTXFutureMarket());
    }
    public static void main(String[] args) throws IOException {
        FTXFutureMarketListener market = new FTXFutureMarketListener();
        market.writeFutureMarket();
    }
}
