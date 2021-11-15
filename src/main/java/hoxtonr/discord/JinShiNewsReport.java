package hoxtonr.discord;

import hoxtonr.project.index.JinShiNews;
import hoxtonr.project.index.model.JinShiNewsModel;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class JinShiNewsReport {
    public static void main(String[] args) throws Exception {
        JinShiNews jinshi = new JinShiNews();
        JDA jda = JDABuilder.createDefault("OTA2NjUxOTQ1ODA3MjU3NjEw.YYbvbA.IVNrOIerJ6QdVZCe4FYBA6DUdgQ")
                .setActivity(Activity.playing("打印钞票"))
                .build();
        jda.awaitReady();
        System.out.println("Finished Building JDA!");
        JinShiNewsModel temp = jinshi.getJinShiNews().get(1);
        JinShiNewsModel mess;
        while(true) {
            try {
                mess = jinshi.getJinShiNews().get(0);
                if (!mess.getId().equals(temp.getId())) {
                    if(mess.getData().getContent().length()<3){
                        System.out.println("消息异常，重新获取中");
                        continue;
                    }
                    String content = mess.getData().getContent();
                    if(mess.getImportant() == 1){
                        jda.getGuilds().get(0).getTextChannelById(Channels.jinShiImportant).sendMessage(newsMessage(mess)).queue();

                    }else if(content.contains("快讯")){
                        jda.getGuilds().get(0).getTextChannelById(Channels.jinShiquickNews).sendMessage(newsMessage(mess)).queue();

                    }else if(content.contains("行情")){
                        jda.getGuilds().get(0).getTextChannelById(Channels.jinShimarket).sendMessage(newsMessage(mess)).queue();
                    }else{
                        jda.getGuilds().get(0).getTextChannelById(Channels.jinShiOther).sendMessage(newsMessage(mess)).queue();
                    }
                    temp = mess;
                }
            }catch(Exception e){
                System.out.println("信息获取失败，正在重新获取。");
                continue;
            }
            Thread.sleep(1000);
        }
    }
    public static String removeSymbol(String mess){
        return mess.replaceAll("<b>","").replaceAll("</b>","")
                .replaceAll("<br>","").replaceAll("</br>","");
    }
    @NotNull
    public static String newsMessage(JinShiNewsModel news){
        if(news.getData().getPic().length()<5){
            return removeSymbol(news.getData().getContent())+"\n美东时间: "+LocalDateTime.now();
        }
        return removeSymbol(news.getData().getContent())+news.getData().getPic()+"\n美东时间: "+LocalDateTime.now();
    }
}
