package hoxtonr.frame.HuobiFrame.Market;

import hoxtonr.frame.Tools.HuobiJsonHandler;

import java.util.List;

public class HuobiSpotMarketImp implements HuobiSpotMarket{
    @Override
    public String HuobiSpotPrice(String cointype) {
        HuobiJsonHandler handler = new HuobiJsonHandler();
        List<String> coinlist = handler.HuobiJsonHand("HuobiSpotPrice.json");
        for(String c : coinlist){
           if(c.contains(cointype)){
               String temp = c.substring(c.indexOf("symbol")).replaceAll("\"","");
               if(temp.substring(temp.indexOf("symbol"),temp.indexOf(",")).replaceAll("symbol:","").equals(cointype)){
                   return temp.substring(temp.indexOf("close"),temp.indexOf("amount")).replaceAll("close:","").replaceAll(",","");
               }
           }
        }
        return null;
    }
    public static void main(String[] args) {
        HuobiSpotMarket market = new HuobiSpotMarketImp();
        System.out.println(market.HuobiSpotPrice("arusdt"));
    }
}
