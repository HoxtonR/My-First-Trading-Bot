package hoxtonr.frame.HuobiFrame.HuobiInfoListener;

import hoxtonr.frame.HuobiFrame.Account.HuobiSpotBalanceImp;

import java.io.IOException;

public class HuobiSpotAccountListener{
    private String username;
    private String apikey;
    private String secretkey;
    private Long accountid;
    public HuobiSpotAccountListener(String username,String apikey, String secretkey,Long accountid){
        this.username = username;
        this.apikey = apikey;
        this.secretkey = secretkey;
        this.accountid = accountid;
    }


    public String HuobiSpotAccount() throws IOException, InterruptedException {
        HuobiSpotBalanceImp huobiSpotBalanceImp = new HuobiSpotBalanceImp(username,apikey,secretkey,accountid);
        return String.valueOf(huobiSpotBalanceImp.getUsdt());
    }
}