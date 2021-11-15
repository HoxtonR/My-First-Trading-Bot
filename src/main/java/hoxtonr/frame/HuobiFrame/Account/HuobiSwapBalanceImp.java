package hoxtonr.frame.HuobiFrame.Account;

import com.huobi.api.crossresponse.account.SwapCrossAccountInfoResponse;
import com.huobi.api.crossservice.crossaccount.CrossAccountAPIServiceImpl;
import java.math.BigDecimal;

public class HuobiSwapBalanceImp {
    private String apikey;
    private String secretkey;
    private Long accountId;
    public HuobiSwapBalanceImp(String apikey, String secretkey, Long accountId){
        this.accountId = accountId;
        this.apikey = apikey;
        this.secretkey = secretkey;
    }
    public BigDecimal swapBalance(String cointype){
        CrossAccountAPIServiceImpl huobiAPIService = new CrossAccountAPIServiceImpl(apikey,secretkey);
        SwapCrossAccountInfoResponse response = huobiAPIService.getSwapCrossAccountInfo(cointype);
        return response.getData().get(0).getWithdrawAvailable();
    }

}
