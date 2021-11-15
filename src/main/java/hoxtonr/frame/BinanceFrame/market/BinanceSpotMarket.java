package hoxtonr.frame.BinanceFrame.market;

import java.io.IOException;
import java.util.HashMap;

public interface BinanceSpotMarket {
    public HashMap<String,Double> BinanceSpotPrice() throws IOException;

}
