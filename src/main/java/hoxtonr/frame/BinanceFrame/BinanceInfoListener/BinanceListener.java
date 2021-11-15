package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.BinanceApiKey;

public class BinanceListener implements Runnable{
    private BinanceSpotPriceListener SpotPriceListener;
    private BinanceSwapPriceListener SwapPriceListener;
    private BinanceSpotTradeRuleListener SpotTradeRuleListener;
    private BinanceSwapTradeRuleListener SwapTradeRuleListener;

    public BinanceListener(){

        SpotPriceListener = new BinanceSpotPriceListener();
        SwapPriceListener = new BinanceSwapPriceListener();
        SpotTradeRuleListener = new BinanceSpotTradeRuleListener();
        SwapTradeRuleListener = new BinanceSwapTradeRuleListener();

    }
    @Override
    public void run() {

   }
    public static void main(String[] args){
        BinanceApiKey api = new BinanceApiKey();
        BinanceAccountListener acc = new BinanceAccountListener("HoxtonR",api.getApikey(),api.getSecretkey());
        BinanceListener lis = new BinanceListener();
        Thread r = new Thread(acc);
        Thread t = new Thread(lis);
        t.start();
        r.start();
    }
}
