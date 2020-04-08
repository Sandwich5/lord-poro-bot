package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Iterator;

@CommandDescriptor(acceptors = {"query"})
public class Query implements ICommand {

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        VoiceChannel currentChannel = event.getGuild().getVoiceChannels().stream()
                .filter(voiceChannel -> voiceChannel.getMembers().contains(event.getMember()))
                .findFirst().orElse(null);
        if (currentChannel == null) {
            System.out.println("Could not find a current voice channel");
            return;
        }

        Iterator<Member> members = currentChannel.getMembers().iterator();
        StringBuilder participants = new StringBuilder().append(nonNullName(members.next()));
        while (members.hasNext()) participants.append(" \n ").append(nonNullName(members.next()));

        EmbedBuilder builder = new EmbedBuilder()
                .setColor(0xc45c3f)
                .setTitle("Members present in voice channel: __" + currentChannel.getName() + "__")
                .addField("Online", participants.toString(), false);

        event.getChannel().sendMessage(builder.build()).complete();
        builder.clear();
    }

    protected String nonNullName(Member member)
    {
        String bot = member.getUser().isBot() ? " `BOT`" : "";
        if (member.getNickname() != null) return member.getNickname() + bot;
        return member.getEffectiveName() + bot;
    }
}
