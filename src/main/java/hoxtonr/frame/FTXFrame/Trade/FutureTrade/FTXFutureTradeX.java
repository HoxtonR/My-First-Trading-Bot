package hoxtonr.frame.FTXFrame.Trade.FutureTrade;

import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.FTXFrame.Builder.JSONBuilder;
import hoxtonr.frame.FTXFrame.Constant.FTXApiKey;
import hoxtonr.frame.FTXFrame.Exceptions.FTXExceptions;
import hoxtonr.frame.FTXFrame.Signature.SignatureGenerator;
import hoxtonr.frame.FTXFrame.WebConnection.WebConnectFactory;
import hoxtonr.frame.FTXFrame.model.FTXOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

public class FTXFutureTradeX {
    private final String HTTPMethod = "POST";
    private final String Path = "/api/orders";
    private final String apikey;
    private final String secretkey;

    public FTXFutureTradeX(String apikey, String secretkey) {
        this.apikey = apikey;
        this.secretkey = secretkey;
    }

    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

    Long gmtNow() {
        return now.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    private String PostLimitRequest(String cointype, String side, BigDecimal price, String type, BigDecimal amount, boolean postOnly) throws Exception {
        HashMap<String, String> Request = new HashMap<>();
        SignatureGenerator sign = new SignatureGenerator();
        JSONBuilder builder = new JSONBuilder();
        Request.put("market", cointype);
        Request.put("side", side);
        Request.put("price", String.valueOf(price));
        Request.put("type", type);
        Request.put("size", String.valueOf(amount));
        Request.put("postOnly", String.valueOf(postOnly));
        String JsonRequest = builder.buildJson(Request);
        String Signature = sign.generateSignature(secretkey, gmtNow() + HTTPMethod + Path + JsonRequest);
        HashMap<String, String> mainHash = new HashMap<>();
        mainHash.put("ApiKey", apikey);
        mainHash.put("Signature", Signature);
        mainHash.put("RequestBody", JsonRequest);
        mainHash.put("EndPoint", Path);
        mainHash.put("Time", String.valueOf(gmtNow()));
        WebConnectFactory connect = new WebConnectFactory();
        return connect.PostRequest(mainHash);
    }

    private String PostMarketRequest(String cointype, String side, String type, BigDecimal amount) throws Exception {
        HashMap<String, String> Request = new HashMap<>();
        SignatureGenerator sign = new SignatureGenerator();
        JSONBuilder builder = new JSONBuilder();
        Request.put("market", cointype);
        Request.put("side", side);
        Request.put("type", type);
        Request.put("size", String.valueOf(amount));
        Request.put("price", "null");
        String JsonRequest = builder.buildJson(Request);
        String Signature = sign.generateSignature(secretkey, gmtNow() + HTTPMethod + Path + JsonRequest);
        HashMap<String, String> mainHash = new HashMap<>();
        mainHash.put("ApiKey", apikey);
        mainHash.put("Signature", Signature);
        mainHash.put("RequestBody", JsonRequest);
        mainHash.put("EndPoint", Path);
        mainHash.put("Time", String.valueOf(gmtNow()));
        WebConnectFactory connect = new WebConnectFactory();
        return connect.PostRequest(mainHash);
    }

    public FTXOrder FTXFutureTradeLimitBuy(String cointype, BigDecimal price, BigDecimal amount, boolean postOnly) throws Exception {
        String mess = PostLimitRequest(cointype, "buy", price, "limit", amount, postOnly);
        JSONObject json = JSONObject.parseObject(mess);
        if (!json.getString("success").equals("true")) {
            throw new FTXExceptions("Order error: " + json.getString("error"));
        }
        JSONObject order = json.getJSONObject("result");
        FTXOrder orderInfo = new FTXOrder();
        orderInfo.setFuture(order.getString("future"));
        orderInfo.setCreatedAt(order.getString("createdAt"));
        orderInfo.setFilledSize(order.getDouble("filledSize"));
        orderInfo.setId(order.getLong("id"));
        orderInfo.setPostOnly(order.getBoolean("postOnly"));
        orderInfo.setSide(order.getString("side"));
        orderInfo.setIoc(order.getBoolean("ooc"));
        orderInfo.setRemainingSize(order.getDouble("remainingSize"));
        orderInfo.setPrice(order.getDouble("price"));
        orderInfo.setStatus(order.getString("status"));
        orderInfo.setStatus(order.getString("market"));

        return orderInfo;
    }

    public FTXOrder FTXFutureTradeLimitSell(String cointype, BigDecimal price, BigDecimal amount, boolean postOnly) throws Exception {
        String mess = PostLimitRequest(cointype, "sell", price, "limit", amount, postOnly);
        JSONObject json = JSONObject.parseObject(mess);
        if (!json.getString("success").equals("true")) {
            throw new FTXExceptions("Order error: " + json.getString("error"));
        }
        JSONObject order = json.getJSONObject("result");
        FTXOrder orderInfo = new FTXOrder();
        orderInfo.setFuture(order.getString("future"));
        orderInfo.setCreatedAt(order.getString("createdAt"));
        orderInfo.setFilledSize(order.getDouble("filledSize"));
        orderInfo.setId(order.getLong("id"));
        orderInfo.setPostOnly(order.getBoolean("postOnly"));
        orderInfo.setSide(order.getString("side"));
        orderInfo.setIoc(order.getBoolean("ooc"));
        orderInfo.setRemainingSize(order.getDouble("remainingSize"));
        orderInfo.setPrice(order.getDouble("price"));
        orderInfo.setStatus(order.getString("status"));
        orderInfo.setStatus(order.getString("market"));
        return orderInfo;
    }

    public FTXOrder FTXFutureTradeMarketBuy(String cointype, BigDecimal amount) throws Exception {
        String mess = PostMarketRequest(cointype, "buy", "market", amount);
        JSONObject json = JSONObject.parseObject(mess);
        if (!json.getString("success").equals("true")) {
            throw new FTXExceptions("Order error: " + json.getString("error"));
        }
        JSONObject order = json.getJSONObject("result");
        FTXOrder orderInfo = new FTXOrder();
        orderInfo.setFuture(order.getString("future"));
        orderInfo.setCreatedAt(order.getString("createdAt"));
        orderInfo.setFilledSize(order.getDouble("filledSize"));
        orderInfo.setId(order.getLong("id"));
        orderInfo.setPostOnly(order.getBoolean("postOnly"));
        orderInfo.setSide(order.getString("side"));
        orderInfo.setIoc(order.getBoolean("ooc"));
        orderInfo.setRemainingSize(order.getDouble("remainingSize"));
        orderInfo.setPrice(order.getDouble("price"));
        orderInfo.setStatus(order.getString("status"));
        orderInfo.setStatus(order.getString("market"));
        return orderInfo;
    }

    public FTXOrder FTXFutureTradeMarketSell(String cointype, BigDecimal amount) throws Exception {
        String mess = PostMarketRequest(cointype, "sell", "market", amount);
        JSONObject json = JSONObject.parseObject(mess);
        if (!json.getString("success").equals("true")) {
            throw new FTXExceptions("Order error: " + json.getString("error"));
        }
        JSONObject order = json.getJSONObject("result");
        FTXOrder orderInfo = new FTXOrder();
        orderInfo.setFuture(order.getString("future"));
        orderInfo.setCreatedAt(order.getString("createdAt"));
        orderInfo.setFilledSize(order.getDouble("filledSize"));
        orderInfo.setId(order.getLong("id"));
        orderInfo.setPostOnly(order.getBoolean("postOnly"));
        orderInfo.setSide(order.getString("side"));
        orderInfo.setIoc(order.getBoolean("ooc"));
        orderInfo.setRemainingSize(order.getDouble("remainingSize"));
        orderInfo.setPrice(order.getDouble("price"));
        orderInfo.setStatus(order.getString("status"));
        orderInfo.setStatus(order.getString("market"));
        return orderInfo;
    }

    public static void main(String[] args) throws Exception {
        FTXFutureTradeX trade = new FTXFutureTradeX(FTXApiKey.apikey, FTXApiKey.secretkey);
        System.out.println(trade.FTXFutureTradeLimitBuy("ETH-PERP", BigDecimal.valueOf(3000), BigDecimal.valueOf(0.002), false));
    }
}
