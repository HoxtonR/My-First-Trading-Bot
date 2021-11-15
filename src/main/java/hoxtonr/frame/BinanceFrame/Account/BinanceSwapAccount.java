package hoxtonr.frame.BinanceFrame.Account;

public interface BinanceSwapAccount {
    public String BinanceSwapBalance(String cointype);
    public String BinanceSwapShortBalance(String cointype);
    public String BinanceSwapUsdt();
    public String getSwapTransferBalance();
    public void getPositions();

}
