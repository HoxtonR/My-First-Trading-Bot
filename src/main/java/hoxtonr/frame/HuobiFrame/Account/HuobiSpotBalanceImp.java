package hoxtonr.frame.HuobiFrame.Account;

import hoxtonr.frame.Tools.FileReader;
import com.huobi.api.request.transfer.UsdtSwapTransferRequest;
import com.huobi.api.response.transfer.UsdtSwapTransferResponse;
import com.huobi.api.service.transfer.TransferApiServiceImpl;
import com.huobi.client.AccountClient;
import com.huobi.client.req.account.AccountBalanceRequest;
import com.huobi.constant.HuobiOptions;
import com.huobi.model.account.AccountBalance;
import hoxtonr.project.HighRiskProject.NewCoinGrabber.constant.HuobiApiKey;

import java.math.BigDecimal;

public class HuobiSpotBalanceImp {
    private String username;
    private String apikey;
    private String secretkey;
    private Long accountId;
    public HuobiSpotBalanceImp(String username,String apikey, String secretkey, Long accountId){
        this.username = username;
        this.accountId = accountId;
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    public BigDecimal getBalance(String cointype) {
        AccountClient accountService = AccountClient.create(HuobiOptions.builder()
                .apiKey(apikey)
                .secretKey(secretkey)
                .build());
        AccountBalance accountBalance = accountService.getAccountBalance(AccountBalanceRequest.builder()
                .accountId(accountId)
                .build());
        for (int i = 0; i < accountBalance.getList().size(); i++) {
            if (accountBalance.getList().get(i).getCurrency().equals(cointype.replace("usdt","")) && accountBalance.getList().get(i).getType().equals("trade")) {
                return accountBalance.getList().get(i).getBalance();
            }
        }
        return null;
    }
    public BigDecimal getUsdt() {
        AccountClient accountService = AccountClient.create(HuobiOptions.builder()
                .apiKey(apikey)
                .secretKey(secretkey)
                .build());
        AccountBalance accountBalance = accountService.getAccountBalance(AccountBalanceRequest.builder()
                .accountId(accountId)
                .build());
        for (int i = 0; i < accountBalance.getList().size(); i++) {
            if (accountBalance.getList().get(i).getCurrency().equals("usdt") && accountBalance.getList().get(i).getType().equals("trade")) {
                return accountBalance.getList().get(i).getBalance();
            }
        }
        return null;
    }
    public void transfer(BigDecimal amount, String cointype, String from, String to){
        TransferApiServiceImpl transferApiService = new TransferApiServiceImpl(apikey,secretkey);
        /**
         * 参数名称	            是否必须	类型	    描述	                                                    取值范围
         * from	                true	string	来源业务线账户，取值：spot(币币)、linear-swap(正向永续合约)	e.g. spot
         * to	                true	string	目标业务线账户，取值：spot(币币)、linear-swap(正向永续合约)	e.g. linear-swap
         * currency	            true	string	币种,支持大小写	                                        e.g. usdt
         * amount	            true	decimal	划转金额
         * margin_account	    true	string	保证金账户	                                            e.g. btc-usdt、eth-usdt*/

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
    public String spotusdt(){
        FileReader reader = new FileReader();
        while(true) {
            String c = reader.readFileByByte(username + "-HuobiBalanceSpot.json");
            if (c != null&&c.length()!=0) {
                return c;
            } else {
               System.out.println("余额获取异常，正在重新获取！");
            }
        }
    }
    public static void main(String[] args){
        HuobiSpotBalanceImp s = new HuobiSpotBalanceImp("Vida", HuobiApiKey.getApi_key(),HuobiApiKey.getSecret_key(),HuobiApiKey.getAccountId());
        System.out.println(s.getUsdt());


    }
}
