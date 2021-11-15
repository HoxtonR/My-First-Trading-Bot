package hoxtonr.frame.FTXFrame.Account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.frame.FTXFrame.Signature.SignatureGenerator;
import hoxtonr.frame.FTXFrame.WebConnection.WebConnectFactory;
import hoxtonr.frame.FTXFrame.model.FTXBalance;
import hoxtonr.frame.FTXFrame.model.FTXPosition;
import hoxtonr.project.LowRiskProject.Arbitrage.ApiKey.FTXAccount;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FTXWallet {
    private final String apikey;
    private final String secretkey;
    private final String HTTPMethod = "GET";
    private final String Path = "/api/wallet/balances";
    private final String Path2 = "/api/positions";

    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
    Long gmtNow() {
        return now.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
    public FTXWallet(String apieky, String secretkey){
        this.apikey = apieky;
        this.secretkey = secretkey;
    }
    private String getBalanceInternal() throws Exception {
        HashMap<String,String> infoHash = new HashMap<>();
        infoHash.put("ApiKey",apikey);
        SignatureGenerator g = new SignatureGenerator();
        String Signature = g.generateSignature(secretkey,gmtNow()+HTTPMethod+Path);
        infoHash.put("Time",String.valueOf(gmtNow()));
        infoHash.put("Signature",Signature);
        infoHash.put("EndPoint",Path);
        WebConnectFactory f = new WebConnectFactory();
        return f.GetRequest(infoHash);
    }
    private String getPositionsInternal() throws Exception {
        HashMap<String,String> infoHash = new HashMap<>();
        infoHash.put("ApiKey",apikey);
        SignatureGenerator g = new SignatureGenerator();
        String Signature = g.generateSignature(secretkey,gmtNow()+HTTPMethod+Path2);
        infoHash.put("Time",String.valueOf(gmtNow()));
        infoHash.put("Signature",Signature);
        infoHash.put("EndPoint",Path2);
        WebConnectFactory f = new WebConnectFactory();
        return f.GetRequest(infoHash);
    }
    public List<FTXBalance> getFTXBalance() throws Exception {
        String c = getBalanceInternal();
        List<FTXBalance> list = new ArrayList<>();
        if (c != null) {
            JSONArray array = JSONObject.parseObject(c).getJSONArray("result");
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                FTXBalance balanceModel = new FTXBalance();
                balanceModel.setCoin(obj.getString("coin"));
                balanceModel.setFree(obj.getDouble("free"));
                balanceModel.setTotal(obj.getDouble("total"));
                balanceModel.setUsdValue(obj.getDouble("usdValue"));
                balanceModel.setSpotBorrow(obj.getDouble("spotBorrow"));
                balanceModel.setAvailableWithoutBorrow(obj.getDouble("availableWithoutBorrow"));
                list.add(balanceModel);
            }
        }
        return list;
    }
    public List<FTXPosition> getFTXPosition() throws Exception {
        String m = getPositionsInternal();
        JSONObject o = JSONObject.parseObject(m);
        System.out.println(m);
        JSONArray array = o.getJSONArray("result");
        System.out.println(array);
        List<FTXPosition> p1 = new ArrayList<>();
        for(int i = 0; i< array.size(); i++){
            FTXPosition p = new FTXPosition();
            p.setFuture(array.getJSONObject(i).getString("future"));
            p.setSide(array.getJSONObject(i).getString("side"));
            p.setSize(array.getJSONObject(i).getDouble("size"));
            p.setNetSize(array.getJSONObject(i).getDouble("netSize"));
            p.setLongOrderSize(array.getJSONObject(i).getDouble("longOrderSize"));
            p.setCost(array.getJSONObject(i).getDouble("cost"));
            p.setEntryPrice(array.getJSONObject(i).getDouble("entryPrice"));
            p.setUnrealizedPnl(array.getJSONObject(i).getDouble("unrealizedPnl"));
            p.setRealizedPnl(array.getJSONObject(i).getDouble("realizedPnl"));
            p.setInitialMarginRequirement(array.getJSONObject(i).getDouble("initialMarginRequirement"));
            p.setMaintenanceMarginRequirement(array.getJSONObject(i).getDouble("maintenanceMarginRequirement"));
            p.setOpenSize(array.getJSONObject(i).getDouble("openSize"));
            p.setCollateralUsed(array.getJSONObject(i).getDouble("collateralUsed"));
            p1.add(p);
        }
        return p1;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());
        FTXWallet w = new FTXWallet(FTXAccount.getApikey(),FTXAccount.getSecretkey());
        System.out.println(w.getFTXPosition());

        System.out.println(LocalDateTime.now());
    }

}
