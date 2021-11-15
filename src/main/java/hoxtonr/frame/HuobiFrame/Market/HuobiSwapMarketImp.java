package hoxtonr.frame.HuobiFrame.Market;

import hoxtonr.frame.Tools.HuobiJsonHandler;

import java.util.List;

public class HuobiSwapMarketImp implements HuobiSwapMarket{

    @Override
    public String HuobiSwapPrice(String cointype) {
        HuobiJsonHandler hand = new HuobiJsonHandler();
        List<String> c  = hand.HuobiSwapJsonHand("HuobiSwapPrice.json");
        for(String a : c){
            a = a.replaceAll("\"","");
            if(a.contains(cointype)) {
                String ta = a.substring(a.indexOf("contract_code"),a.indexOf("open")).replaceAll("contract_code:","").replaceAll(",","");
                if(ta.equals(cointype)) {
                    return a.substring(a.indexOf("close"), a.indexOf("low")).replaceAll("close:", "").replaceAll(",", "");
                }
            }
        }
        return null;
    }
    public static void main(String[] args){
        HuobiSwapMarket marke = new HuobiSwapMarketImp();
        System.out.println(marke.HuobiSwapPrice("AR-USDT"));
    }
}
