package hoxtonr.project.LowRiskProject.Arbitrage.robot;


import hoxtonr.frame.BinanceFrame.trade.BinanceSwapTrade;
import hoxtonr.frame.BinanceFrame.trade.BinanceSwapTradeImp;

public class FundingRobot  {
    private final String apikey;
    private final String secretkey;
    private BinanceSwapTrade trade;

    public FundingRobot(String apikey, String secretkey) {
        this.apikey = apikey;
        this.secretkey = secretkey;
        trade = new BinanceSwapTradeImp(apikey, secretkey);
    }

    public boolean fundingTime(int hour, int min, int sec) {
        if ((hour == 3 || hour == 11 || hour == 19) && min == 59 && (sec == 56 || sec == 57 || sec == 58)) {
            return true;
        }
        return false;
    }
}
 /*   public int[] TradeStandard(String cointype) throws IOException {
        int[] st = new int[2];
        BinanceTradeStandard standard = new BinanceTradeStandard();
        BinanceSwapTradeRuleListener lis = new BinanceSwapTradeRuleListener();
        HashMap<String,Integer[]> m = standard.TradeRuleHandler(lis.BinanceSwapTradeRule());  //获取交易规则
        String[] d = m.get(cointype);
        String amountstandard = d[0];
        String pricestandard = d[1];
        if(amountstandard.contains(".")){
            st[0] = amountstandard.substring(amountstandard.indexOf("."), amountstandard.indexOf("1")).length();
        }
        if(pricestandard.contains(".")){
            st[1] = pricestandard.substring(pricestandard.indexOf("."), pricestandard.indexOf("1")).length();
        }
        return st;
    }
    public void cancelSwaptOrder(String cointype) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        try {
            syncRequestClient.cancelAllOpenOrder(cointype);
        } catch (com.binance.api.client.exception.BinanceApiException e) {
            System.out.println(e.getError().getMsg());
        }
    }
    public Order getSwaporder(String cointype, Long orderId) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(apikey, secretkey,
                options);
        return syncRequestClient.getOrder(cointype,orderId,null);
    }
    public void coreFunction(String cointype, int leverage) throws IOException, InterruptedException {
        System.out.println(LocalDateTime.now());
        BinanceSwapMarket market = new BinanceSwapMarketImp();
        BinanceSwapTrade trade = new BinanceSwapTradeImp(apikey,secretkey);
        BinanceSwapAccount account = new BinanceSwapAccountImp("",apikey,secretkey);
        int[] sd = TradeStandard(cointype);
        Long startTime = System.currentTimeMillis();
        double price = Double.parseDouble(market.BinanceSwapMarketPrice(cointype));  //获取价格
        double amount = Double.parseDouble(account.BinanceSwapUsdt())/ price * leverage * 0.95; //获取数量
        trade.BinanceSwapMarketSellOpen(cointype,String.valueOf(BigDecimal.valueOf(amount).setScale(sd[0],RoundingMode.DOWN)),leverage);
        System.out.println(LocalDateTime.now());
        Thread.sleep(3000);
        BigDecimal sellamount = BigDecimal.valueOf(Double.parseDouble(account.BinanceSwapShortBalance(cointype))).setScale(sd[0],RoundingMode.DOWN);
        trade.BinanceSwapMarketBuyClose(cointype,String.valueOf(sellamount).replace("-",""),leverage);
    }
    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            LocalDateTime time = LocalDateTime.now();
            if (true) {
                System.out.println("买入线程启动！");
                BinanceSwapFundingRate rate = new BinanceSwapFundingRateImp();
                String cointype = rate.BinanceNextFundingRate().get(rate.BinanceNextFundingRate().size()-1).getSymbol();
                coreFunction(cointype,50);
                break;
            }
            Thread.sleep(100);
        }
    }
}*/
