package src;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Bot
{

    public static String prefix = ".";
    public static JDA jda;

    public static void main(String args[]) throws Exception
    {
        jda = new JDABuilder(AccountType.BOT)
                .setToken("NDA5MzU1MjA0NjQ4MTA4MDQz.Xohlcw.yc-y5u4qJSYPwB1MG2IN6z3FU_w")
                .build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.of(Activity.ActivityType.DEFAULT, "In Development"));

        jda.addEventListener(new CommandDispatch());
    }

}
