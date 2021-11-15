package hoxtonr.project.HighRiskProject.NewCoinGrabber.twitter;

import hoxtonr.frame.Tools.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BanList {
    public List<String> readBanList(){
        FileReader reader = new FileReader();
        String c =  reader.readFileByByte("BanList.txt");
        List<String> temp = Arrays.asList(c.split(","));
        return temp;
    }
    public void writeBanList(List<String> banList) throws IOException {
        FileWriter writer = new FileWriter("BanList.txt",true);
        List<String> existCoins = readBanList();
        for(String c : banList){
            if (!existCoins.contains(c)) {
                writer.append(c).append(",");
            }
        }
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        BanList list = new BanList();
        List<String> l = new ArrayList<>();
        l.add("BTCUSDT");
        list.writeBanList(l);
    }

}
