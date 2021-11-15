package hoxtonr.frame.BinanceFrame.Account;

import com.binance.api.client.domain.FutureTransferType;

public interface BinanceSpotAccount {
    public String BinanceSpotBalance(String cointype);
    public String BinanceSpotUsdt();
    public String BinanceSpotBusd();
    public void BinanceSpotTransfer(String amount, FutureTransferType fu);

}
