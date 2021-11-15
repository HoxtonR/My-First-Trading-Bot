package hoxtonr.project.HighRiskProject.NewCoinGrabber.Trade;


import hoxtonr.frame.HuobiFrame.Account.HuobiSpotBalanceImp;
import hoxtonr.frame.HuobiFrame.Account.HuobiSwapBalanceImp;
import hoxtonr.frame.HuobiFrame.Market.HuobiSpotMarket;
import hoxtonr.frame.HuobiFrame.Market.HuobiSpotMarketImp;
import hoxtonr.frame.HuobiFrame.Market.HuobiSwapMarket;
import hoxtonr.frame.HuobiFrame.Market.HuobiSwapMarketImp;
import hoxtonr.frame.HuobiFrame.Trade.*;
import hoxtonr.frame.Tools.HuobiJsonHandler;
import com.huobi.api.crossrequest.trade.SwapCrossCancelRequest;
import com.huobi.api.crossrequest.trade.SwapCrossOpenordersRequest;
import com.huobi.api.crossresponse.account.SwapCrossPositionInfoResponse;
import com.huobi.api.crossresponse.trade.SwapCrossOpenordersResponse;
import com.huobi.api.crossservice.crossaccount.CrossAccountAPIServiceImpl;
import com.huobi.api.crossservice.crosstrade.CrossTradeAPIServiceImpl;
import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import com.huobi.api.exception.ApiException;
import com.huobi.api.request.transfer.UsdtSwapTransferRequest;
import com.huobi.api.response.transfer.UsdtSwapTransferResponse;
import com.huobi.api.service.transfer.TransferApiServiceImpl;
import com.huobi.client.TradeClient;
import com.huobi.client.req.trade.OpenOrdersRequest;
import com.huobi.constant.HuobiOptions;
import com.huobi.model.trade.Order;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.robot.TelegramRobot;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.HuobiApiKey;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HuobiTradeAsy implements Runnable {
    private String[] cointypes;
    private String username;
    private String apikey;
    private String secretkey;
    private Long accountID;
    private String balance;

    public HuobiTradeAsy(String[] cointypes, String username) {
        this.apikey = HuobiApiKey.getApi_key();
        this.secretkey = HuobiApiKey.getSecret_key();
        this.accountID = HuobiApiKey.getAccountId();
        this.cointypes = cointypes;
        this.username = username;
        HuobiSpotBalanceImp spotAccount = new HuobiSpotBalanceImp(username, apikey, secretkey, accountID);
        balance = String.valueOf(BigDecimal.valueOf(Double.parseDouble(spotAccount.spotusdt())).setScale(2,RoundingMode.DOWN));
    }

    public static String swapWordHandle(String coins) {
        String c = coins;
        StringBuffer sb = new StringBuffer();
        sb.append(c).insert(c.indexOf("USDT"), "-");
        return sb.toString();
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("-------[火币买入线程启动]--------");
        System.out.println(LocalDateTime.now());
        System.out.println("------------------");
        HuobiSpotBalanceImp spotAccount = new HuobiSpotBalanceImp(username, apikey, secretkey, accountID);
        TelegramRobot t = new TelegramRobot();
        HuobiTransferX transfer = new HuobiTransferX(apikey,secretkey,accountID);
        double old = Double.parseDouble(spotAccount.spotusdt());
        HuobiSwapMarket market = new HuobiSwapMarketImp();
        List<String> coins = Arrays.asList(cointypes);
        List<String> SpotCoins = new ArrayList<>();
        List<String> SwapCoins = new ArrayList<>();

        for (String c : coins) {
            String coin = swapWordHandle(c);
            if (market.HuobiSwapPrice(coin) != null) {
                SwapCoins.add(coin);
            }
        }
        if (!SwapCoins.isEmpty()) {
            System.out.println("-------[火币买入断点1]--------");
            System.out.println(LocalDateTime.now());
            System.out.println("------------------");
            transfer.HuobiTransferX_SpotToUsdtFuture(balance,"USDT");
            System.out.println("-------[火币买入断点2]--------");
            System.out.println(LocalDateTime.now());
            System.out.println("------------------");
            BigDecimal value = BigDecimal.valueOf(Double.parseDouble(balance) / SwapCoins.size()).setScale(2, RoundingMode.DOWN);
            for (String d : SwapCoins) {
                swapBuy(d,String.valueOf(value.doubleValue()*0.85),market.HuobiSwapPrice(d));
                SwapSellStr(d,market.HuobiSwapPrice(d));
            }
            System.out.println("-------[火币合约买入线程结束]--------");
            System.out.println(LocalDateTime.now());
            System.out.println("------------------");
            Thread.sleep(150000);
            HuobiSwapBalanceImp swap = new HuobiSwapBalanceImp(apikey,secretkey,accountID);
            transfer.HuobiTransferX_UsdtFutureToSpot(String.valueOf(swap.swapBalance("USDT").setScale(2,RoundingMode.DOWN)),"USDT");
            Thread.sleep(10000);
            BigDecimal now = BigDecimal.valueOf(Double.parseDouble(spotAccount.spotusdt())-old).setScale(2,RoundingMode.DOWN);
            t.sendmessage("【"+username+"的火币】:本次总盈利为: " + now);
            return;
        }
        HuobiSpotMarket market2 = new HuobiSpotMarketImp();
        for(String q : coins){
            String temp = q.toLowerCase(Locale.ROOT);
            if(market2.HuobiSpotPrice(temp)!=null){
                SpotCoins.add(temp);
            }
        }
        System.out.println("-------[火币买入断点3]--------");
        System.out.println(LocalDateTime.now());
        System.out.println("------------------");
        if(!SpotCoins.isEmpty()){
            Double quantity = Double.parseDouble(balance)/SpotCoins.size()*0.85;
            for(String e : SpotCoins){
                spotBuy(e,String.valueOf(quantity));
                SpotSellStr(e,market2.HuobiSpotPrice(e));
            }
            System.out.println("-------[火币现货买入线程结束]--------");
            System.out.println(LocalDateTime.now());
            System.out.println("------------------");
            Thread.sleep(150000);
            BigDecimal now = BigDecimal.valueOf(Double.parseDouble(spotAccount.spotusdt())-old).setScale(2,RoundingMode.DOWN);
            t.sendmessage("【"+username+"的火币】:本次总盈利为: " + now);

        }
    }

    public void swapBuy(String cointype, String quantity, String price2) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("-------[火币买入断点]"+cointype+"--------");
                System.out.println(LocalDateTime.now());
                System.out.println("------------------");
                TelegramRobot t = new TelegramRobot();
                HuobiSwapTradeXMarket trade = new HuobiSwapTradeXMarket(apikey, secretkey, accountID);
                HuobiSwapTradeX limittrade = new HuobiSwapTradeX(apikey,secretkey,accountID);
                HuobiJsonHandler hand = new HuobiJsonHandler();
                BigDecimal precision[] = hand.HuobiSwapTradeRuleHandler(cointype);
                double price = Double.parseDouble(price2) * 1.015;
                BigDecimal realprice = BigDecimal.valueOf(price).setScale(precision[0].intValue(),RoundingMode.DOWN);
                BigDecimal volume = BigDecimal.valueOf(Math.floor(Double.parseDouble(quantity) / price / precision[1].doubleValue() * 3)).setScale(0, RoundingMode.DOWN);
                String c = limittrade.HuobiSwapLimitBuyX(cointype, String.valueOf(volume.longValue()),String.valueOf(realprice), DirectionEnum.BUY.getValue(), OffsetEnum.OPEN.getValue(), 3);
                t.sendmessage("【"+username+"的火币合约】执行买入: "+cointype+"\n"+"发起时间为:"+ LocalDateTime.now());
                if(c.contains("error")&&c.contains("1039")){
                    HuobiSwapMarket market = new HuobiSwapMarketImp();
                    double reprice = Double.parseDouble(market.HuobiSwapPrice(cointype)) * 1.01;
                    BigDecimal reprice2 = BigDecimal.valueOf(reprice).setScale(precision[0].intValue(),RoundingMode.DOWN);
                    BigDecimal volume2 = BigDecimal.valueOf(Math.floor(Double.parseDouble(quantity) /reprice/ precision[1].doubleValue() * 3)).setScale(0, RoundingMode.DOWN);
                    limittrade.HuobiSwapLimitBuyX(cointype, String.valueOf(volume2.longValue()),String.valueOf(reprice2), DirectionEnum.BUY.getValue(), OffsetEnum.OPEN.getValue(), 3);
                    t.sendmessage("【"+username+"的火币合约】买入异常，执行重新挂单: "+cointype+"\n"+"发起时间为:"+ LocalDateTime.now());
                }
            }
        }).start();
    }

    public void spotBuy(String cointype, String quantity2) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("-------[火币买入断点]"+cointype+"--------");
                System.out.println(LocalDateTime.now());
                System.out.println("------------------");
                TelegramRobot t = new TelegramRobot();
                HuobiSpotTradeXMarket trade = new HuobiSpotTradeXMarket(apikey, secretkey, accountID);
                HuobiJsonHandler hand = new HuobiJsonHandler();
                BigDecimal quantity = BigDecimal.valueOf(Double.parseDouble(quantity2)).setScale(2,RoundingMode.DOWN);
                trade.HuobiSpotLimitBuyX(cointype, String.valueOf(quantity));
                t.sendmessage("【"+username+"的火币现货】执行买入: "+cointype+"\n"+"发起时间为:"+LocalDateTime.now());
            }
        }).start();
    }

    public void SwapSellStr(String cointype, String price2) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TelegramRobot t = new TelegramRobot();
                HuobiSwapCrossTrade trade = new HuobiSwapCrossTrade(apikey,secretkey);
                HuobiJsonHandler hand = new HuobiJsonHandler();
                BigDecimal[] precision = hand.HuobiSwapTradeRuleHandler(cointype);
                Thread.sleep(2000);
                Double volume = getVolume(cointype);
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(price2)*1.2).setScale(precision[0].intValue(),RoundingMode.DOWN);
                for(SwapCrossOpenordersResponse.DataBean.OrdersBean a : getSwapOrder(cointype)){
                    if(a.getDirection().equals(DirectionEnum.BUY.getValue())){
                        t.sendmessage("【"+username+"的火币合约】:检测到"+cointype+"有未成交买单，执行取消!");
                        cancelSwapOrder(cointype,String.valueOf(a.getOrderId()));
                    }
                }
                Long o = trade.HuobiSwapCrossSellLongLimit(cointype,price,volume.longValue(),3);
                t.sendmessage("【"+username+"的火币合约】: "+cointype+"的限价单委托完毕！");
                Thread.sleep(80000);
                if(!getSwapOrder(cointype).get(0).getStatus().equals(6)){
                    t.sendmessage("【"+username+"的火币合约】: "+cointype+"的限价单未成交，正在进行分批卖出！");
                    cancelSwapOrder(cointype,String.valueOf(o));
                    trade.HuobiSwapCrossSellLongMarket(cointype,volume.longValue()/ 3,3);
                    Thread.sleep(20000);
                    trade.HuobiSwapCrossSellLongMarket(cointype,volume.longValue()/ 2,3);
                    Thread.sleep(20000);
                    Double c = getVolume(cointype);
                    trade.HuobiSwapCrossSellLongMarket(cointype,c.longValue(),3);

                  }
            }
        }).start();
    }

    public void SpotSellStr(String cointype, String price2) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TelegramRobot t = new TelegramRobot();
                HuobiSpotTradeImp trade = new HuobiSpotTradeImp(apikey,secretkey,accountID);
                HuobiJsonHandler hand = new HuobiJsonHandler();
                Integer[] precision = hand.HuobiSpotTradeRuleHandler(cointype);
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(price2)*1.2).setScale(precision[0],RoundingMode.DOWN);
                HuobiSpotBalanceImp account = new HuobiSpotBalanceImp(username,apikey,secretkey,accountID);
                Thread.sleep(2000);
                BigDecimal quantity = account.getBalance(cointype).setScale(precision[1],RoundingMode.DOWN);
                BigDecimal quantity2 = BigDecimal.valueOf(quantity.doubleValue()/3).setScale(precision[1],RoundingMode.DOWN);
                for(Order c : getSpotOrder(cointype)){
                    if(c.getType().equals("buy-limit")){
                        t.sendmessage("【"+username+"的火币现货】: "+cointype+"的限价单未成交，正在进行分批卖出！");
                        cancelSpotOrder(c.getId());
                    }
                }
                Long o = trade.spotSell(cointype,quantity,price);   //挂卖出限价单
                t.sendmessage("【"+username+"的火币现货】: "+cointype+"的限价单委托完毕！");
                Thread.sleep(80000);  //延迟80秒
                if(!getSpotOrder(cointype).get(0).getState().equals("filled")) {
                    t.sendmessage("【"+username+"的火币现货】: "+cointype+"的限价单未成交，正在进行分批卖出！");
                    cancelSpotOrder(o);
                    trade.spotMarketSELL(cointype,quantity2);
                    Thread.sleep(20000);
                    trade.spotMarketSELL(cointype,quantity2);
                    Thread.sleep(20000);
                    trade.spotMarketSELL(cointype,quantity2);

                }
            }
        }).start();
    }
    public void transfer(BigDecimal amount, String cointype, String from, String to){
        TransferApiServiceImpl transferApiService = new TransferApiServiceImpl(apikey,secretkey);
        UsdtSwapTransferRequest request = UsdtSwapTransferRequest.builder()
                .from(from)
                .to(to)
                .margin_account(cointype)
                .currency("usdt")
                .amount(amount)
                .build();
        UsdtSwapTransferResponse response = transferApiService.transfer(request);
        System.out.println(response);
    }

    public Double getVolume(String cointype) {
        try {
            CrossAccountAPIServiceImpl huobiAPIService = new CrossAccountAPIServiceImpl(apikey, secretkey);
            SwapCrossPositionInfoResponse response = huobiAPIService.getSwapCrossPositionInfo(cointype);
            return response.getData().get(0).getVolume().doubleValue();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public List<Order> getSpotOrder(String cointype) {
        try {
            TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                    .apiKey(apikey)
                    .secretKey(secretkey)
                    .build());
            OpenOrdersRequest getOrderRequest = OpenOrdersRequest.builder()
                    .accountId(accountID)
                    .symbol(cointype)
                    .build();
            return tradeService.getOpenOrders(getOrderRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SwapCrossOpenordersResponse.DataBean.OrdersBean> getSwapOrder(String cointype) {
        try {
            CrossTradeAPIServiceImpl huobiAPIService = new CrossTradeAPIServiceImpl(apikey, secretkey);
            SwapCrossOpenordersRequest request = SwapCrossOpenordersRequest.builder()
                    .contractCode(cointype)
                    .build();
            return huobiAPIService.swapCrossOpenordersRequest(request).getData().getOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancelSwapOrder(String cointype, String orderId) {
        try {
            CrossTradeAPIServiceImpl huobiAPIService = new CrossTradeAPIServiceImpl(apikey, secretkey);
            SwapCrossCancelRequest request = SwapCrossCancelRequest.builder()
                    .contractCode(cointype)
                    .orderId(orderId)
                    .build();
            huobiAPIService.swapCrossCancelRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelSpotOrder(Long OrderId) {
        try {
            TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                    .apiKey(apikey)
                    .secretKey(secretkey)
                    .build());
            tradeService.cancelOrder(OrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        HuobiTradeAsy as = new HuobiTradeAsy(new String[]{"LTCUSDT","XRPUSDT"},"HoxtonR");
        as.run();
    }
}
