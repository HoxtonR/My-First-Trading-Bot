package hoxtonr.project.HighRiskProject.NewCoinGrabber.robot;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.AssetBalance;
import com.client.RequestOptions;
import com.client.SyncRequestClient;
import com.client.model.trade.PositionRisk;
import hoxtonr.frame.BinanceFrame.Account.BinanceSwapAccount;
import hoxtonr.frame.BinanceFrame.Account.BinanceSwapAccountImp;
import hoxtonr.frame.BinanceFrame.trade.*;
import hoxtonr.frame.HuobiFrame.Account.HuobiSpotBalanceImp;
import hoxtonr.frame.HuobiFrame.Account.HuobiSwapBalanceImp;
import hoxtonr.frame.HuobiFrame.Trade.HuobiSpotTradeImp;
import hoxtonr.frame.HuobiFrame.Trade.HuobiTransferX;
import hoxtonr.frame.Tools.BinanceJsonHandler;
import com.huobi.api.crossrequest.trade.SwapCrossOrderRequest;
import com.huobi.api.crossresponse.account.SwapCrossPositionInfoResponse;
import com.huobi.api.crossresponse.trade.SwapCrossOrderResponse;
import com.huobi.api.crossservice.crossaccount.CrossAccountAPIServiceImpl;
import com.huobi.api.crossservice.crosstrade.CrossTradeAPIServiceImpl;
import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import com.huobi.client.AccountClient;
import com.huobi.client.req.account.AccountBalanceRequest;
import com.huobi.constant.HuobiOptions;
import com.huobi.exception.SDKException;
import com.huobi.model.account.AccountBalance;
import com.huobi.model.account.Balance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class QuickFunctions {
    private final String username;
    private final String HuobiApikey;
    private final String HuobiSecretkey;
    private Long HuobiAccountID;
    private String BinanceApikey;
    private String BinanceSecretkey;
    public QuickFunctions(String Username,String HuobiApikey,String HuobiSecretkey,Long HuobiAccountID, String BinanceApikey, String BinanceSecretkey){
        this.username = Username;
        this.HuobiApikey = HuobiApikey;
        this.HuobiSecretkey = HuobiSecretkey;
        this.HuobiAccountID = HuobiAccountID;
        this.BinanceApikey = BinanceApikey;
        this.BinanceSecretkey = BinanceSecretkey;
    }
    public void HuobiSwapCloseAllPosition(){
        CrossAccountAPIServiceImpl huobiAPIService = new CrossAccountAPIServiceImpl(HuobiApikey, HuobiSecretkey);
        SwapCrossPositionInfoResponse response = huobiAPIService.getSwapCrossPositionInfo(null);
        CrossTradeAPIServiceImpl huobiAPIService2 = new CrossTradeAPIServiceImpl(HuobiApikey, HuobiSecretkey);
        for(SwapCrossPositionInfoResponse.DataBean b : response.getData()) {
            if(b.getAvailable().doubleValue()>1) {
                SwapCrossOrderRequest request = SwapCrossOrderRequest.builder()
                        .contractCode(b.getContractCode())
                        .volume(b.getVolume().longValue())
                        .direction(DirectionEnum.SELL)
                        .offset(OffsetEnum.CLOSE)
                        .leverRate(3)
                        .orderPriceType("optimal_20")
                        .build();
                SwapCrossOrderResponse response2 =
                        huobiAPIService2.swapCrossOrderRequest(request);
            }
        }
    }
    public void HuobiTransferSpotToUsdtFuture(String amount) throws Exception {
        HuobiTransferX transfer = new HuobiTransferX(HuobiApikey, HuobiSecretkey,HuobiAccountID);
        HuobiSpotBalanceImp balance = new HuobiSpotBalanceImp(username,HuobiApikey,HuobiSecretkey,HuobiAccountID);
        if(amount.equals("-1")) {
            transfer.HuobiTransferX_SpotToUsdtFuture(String.valueOf(BigDecimal.valueOf(Double.parseDouble(balance.spotusdt())).setScale(2,RoundingMode.DOWN)), "USDT");
        }else{
            transfer.HuobiTransferX_SpotToUsdtFuture(amount,"USDT");
        }
    }
    public void HuobiTransferUsdtFutureToSpot(String amount) throws Exception {
        HuobiTransferX transfer = new HuobiTransferX(HuobiApikey, HuobiSecretkey,HuobiAccountID);
        HuobiSwapBalanceImp balance = new HuobiSwapBalanceImp(HuobiApikey,HuobiSecretkey,HuobiAccountID);
        if(amount.equals("-1")) {
            transfer.HuobiTransferX_UsdtFutureToSpot(String.valueOf(balance.swapBalance("USDT").setScale(2, RoundingMode.DOWN)), "USDT");
        }else{
            transfer.HuobiTransferX_UsdtFutureToSpot(amount,"USDT");
        }
    }
    public void Binancetransferall() throws Exception {
        BinanceTransferX transfer = new BinanceTransferX(BinanceApikey,BinanceSecretkey);
        BinanceSwapAccount account = new BinanceSwapAccountImp(username,BinanceApikey,BinanceSecretkey);
        transfer.BinanceTransferX_UsdtFuturetoSpot("USDT",account.BinanceSwapUsdt());
    }
    public void BinanceSwapCloseAllPosition(){
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(BinanceApikey,BinanceSecretkey,
                options);
        BinanceSwapTrade t= new BinanceSwapTradeImp(BinanceApikey,BinanceSecretkey);
        for(PositionRisk p : syncRequestClient.getPositionRisk()) {
            if (p.getPositionAmt().doubleValue()>0.1) {
                BinanceJsonHandler hand = new BinanceJsonHandler();
               int s=  hand.SwapTradeRuleHandlerQuanOnly(p.getSymbol());
                t.BinanceSwapMarketSellClose(p.getSymbol(),String.valueOf(p.getPositionAmt().setScale(s,RoundingMode.DOWN)),3);
            }
        }
    }
    public void BianceSpotSellAll(){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(BinanceApikey,BinanceSecretkey);
        BinanceApiRestClient client = factory.newRestClient();
        BinanceSpotTrade t = new BinanceSpotTradeImp(BinanceApikey,BinanceSecretkey);
        for(AssetBalance i : client.getAccount().getBalances()){
            if(Double.parseDouble(i.getFree())>0.5){
                if(!i.getAsset().equals("USDT")) {
                    String c = i.getAsset() + "USDT";
                    try {
                        System.out.println(i.getAsset());
                        t.BinanceSpotMarketSell(c, i.getFree());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void HuobiSpotSellAll(){
        AccountClient accountService = AccountClient.create(HuobiOptions.builder()
                .apiKey(HuobiApikey)
                .secretKey(HuobiSecretkey)
                .build());
        AccountBalance accountBalance = accountService.getAccountBalance(AccountBalanceRequest.builder()
                .accountId(HuobiAccountID)
                .build());
        HuobiSpotTradeImp t = new HuobiSpotTradeImp(HuobiApikey,HuobiSecretkey,HuobiAccountID);
        for(Balance b :accountBalance.getList()){
            if(b.getType().equals("trade")&&b.getBalance().doubleValue()>0.1&&(!b.getCurrency().equals("usdt")&&!b.getCurrency().equals("husd"))){
                String c = b.getCurrency() + "usdt";
                System.out.println(c);
                try {
                    t.spotMarketSELL(c, b.getBalance().setScale(1,RoundingMode.DOWN));
                }catch(SDKException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void FTXSwapSellAll(){

    }

}
