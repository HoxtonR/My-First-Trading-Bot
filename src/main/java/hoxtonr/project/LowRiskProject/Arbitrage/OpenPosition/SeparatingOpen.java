package hoxtonr.project.LowRiskProject.Arbitrage.OpenPosition;

import hoxtonr.frame.BinanceFrame.Account.BinanceSwapAccount;
import hoxtonr.frame.BinanceFrame.Account.BinanceSwapAccountImp;
import hoxtonr.frame.BinanceFrame.market.BinanceSwapMarket;
import hoxtonr.frame.BinanceFrame.market.BinanceSwapMarketImp;
import hoxtonr.frame.FTXFrame.Market.FutureMarket.FTXFutureMarket;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.ArbitrageAccount;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.FTXAccount;

public class SeparatingOpen {
    private final String binanceApikey;
    private final String binanceSecretkey;
    private final String ftxApikey;
    private final String ftxSecretkey;
    private BinanceSwapMarket binancemarket;
    private FTXFutureMarket ftxmaket;

    public SeparatingOpen(String binanceApikey, String binanceSecretkey, String ftxApikey, String ftxSecretkey){
        this.binanceApikey = binanceApikey;
        this.binanceSecretkey = binanceSecretkey;
        this.ftxApikey = ftxApikey;
        this.ftxSecretkey = ftxSecretkey;
        this.binancemarket =  new BinanceSwapMarketImp();
        this.ftxmaket = new FTXFutureMarket();

    }
    public void openPosition(String cointype, double amount,double percent,int leverage){
        /*
        逻辑应当是
        获取开仓金额，开仓金额除以20-50得到每次开仓金额
        Binance获取价格，FTX获取价格，当FTX价格低于Binance
        执行第一次挂单，循环检测两个单的状态
        如果不为FILLED 检测NEW或者Outdated
        如果为Outdated，执行重挂，金额相同，如果为NEW 则等待
        如果两单都交易完毕，进行下一次挂单
        直到总开仓金额达到初始值，挂单结束。
        每次挂单的时候检测当前时间，如果超过费率结算时间，则break
         */
        double amountPerPosition = amount*percent;
        BinanceSwapAccount account = new BinanceSwapAccountImp("",binanceApikey,binanceSecretkey);

        while(true) {
            double binanceCoinPrice = Double.parseDouble(binancemarket.BinanceSwapMarketPrice(cointype));
            double ftxCoinPrice = Double.parseDouble(binancemarket.BinanceSwapMarketPrice(cointype));
            if(ftxCoinPrice<binanceCoinPrice){

            }
        }
    }
    public void closePosition(){

    }
    public static void main(String[] args){
        SeparatingOpen trade = new SeparatingOpen(ArbitrageAccount.getApiKey(), ArbitrageAccount.getSecretKey(), FTXAccount.getApikey(),FTXAccount.getSecretkey());
    }

}
