package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;

public class Driver {
    public static void main(String[] args){
        BinanceRobot r = new BinanceRobot(BinanceAccount.getApiKey(),BinanceAccount.getSecretKey());
        Thread t = new Thread(r);
        t.start();
    }
}
