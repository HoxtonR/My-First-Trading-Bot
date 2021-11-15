package hoxtonr.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;

import javax.security.auth.login.LoginException;

public class MoyuRobot {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault("OTA2NjUxOTQ1ODA3MjU3NjEw.YYbvbA.IVNrOIerJ6QdVZCe4FYBA6DUdgQ")
                .setActivity(Activity.playing("打印钞票"))
                .build();
        jda.awaitReady(); // Blocking guarantees that JDA will be completely loaded.
        System.out.println("Finished Building JDA!");
        System.out.println(jda.getGuilds().get(0).getTextChannels());

    }

}
