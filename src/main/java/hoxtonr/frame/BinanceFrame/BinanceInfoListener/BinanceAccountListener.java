package hoxtonr.frame.BinanceFrame.BinanceInfoListener;

public class BinanceAccountListener implements Runnable{
    private String username;
    private String apikey;
    private String secretkey;
    private BinanceSpotAccountListener SpotAccountListener;
    private BinanceSwapAccountListener SwapAccountListener;


    public BinanceAccountListener(String username, String apikey, String secretkey){
        this.username = username;
        this.apikey = apikey;
        this.secretkey = secretkey;
        SpotAccountListener = new BinanceSpotAccountListener(username,apikey,secretkey);
        SwapAccountListener = new BinanceSwapAccountListener(username,apikey,secretkey);


    }
    @Override
    public void run() {


    }
}
