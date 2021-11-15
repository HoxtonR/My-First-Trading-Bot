package hoxtonr.project.HighRiskProject.NewCoinGrabber.robot;

import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class CoinbaseRobot implements Runnable{
//    private final String wurl = "http://18.181.194.248/api/query?secretKey=X5y4zVJ3uJvBahAdrztXdBVy7xVJSt7m";     //测试官网
    private final String wurl = "http://3.112.41.245:8024/api/query?secretKey=X5y4zVJ3uJvBahAdrztXdBVy7xVJSt7m";     //官方官网
   private final String turl = "http://13.113.190.123/api/analyze/tweets?screenName=coinbasepro";  //官方推特
    private final String ceshiurl = "http://13.113.190.123/api/analyze/tweets?screenName=LatoyaM75223897"; //测试推特
   public List<String> SameInCollection(List<String> list1,List<String> list2){
       List<String> collect2 = list1.stream().filter(num -> !list2.contains(num))
               .collect(Collectors.toList());
       return collect2;
   }
    @SneakyThrows
    @Override
    public void run() {

        while(true){
            try{

            Thread.sleep(50);

            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        CoinbaseRobot robot = new CoinbaseRobot();
        Thread main = new Thread(robot);
        main.start();

    }
}
