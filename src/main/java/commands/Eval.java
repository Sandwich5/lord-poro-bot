package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

@CommandDescriptor(acceptors = {"eval"})
public class Eval implements ICommand {
    @Override
    public void handle(GuildMessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder()
                .addField("@" + nonNullName(event.getMember()) + " - fetched your current activities\n",
                        "```json\n" + getActivityJSON(event.getMember().getActivities()) + "```", false);

        event.getChannel().sendMessage(embed.build()).queue();

        //event.getGuild().moveVoiceMember()
    }

    protected String getActivityJSON(List<Activity> activityList)
    {
        if (activityList == null || activityList.isEmpty()) return "No Activities";
        Activity activity = activityList.get(0);

        String sb = "type : " + activity.getType().toString() + "\n" +
                "name : " + activity.getName() + "\n";
        return sb;
    }

    protected String nonNullName(Member member)
    {
        if (member == null) return "someone";
        String bot = member.getUser().isBot() ? " `BOT`" : "";
        if (member.getNickname() != null) return member.getNickname() + bot;
        return member.getEffectiveName() + bot;
    }
}
