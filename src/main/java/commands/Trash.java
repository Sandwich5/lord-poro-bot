package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

@CommandDescriptor(acceptors = {"trash"})
public class Trash implements ICommand {
    @Override
    public void handle(GuildMessageReceivedEvent event) {
        VoiceChannel trash = event.getGuild().getChannels().stream()
                .filter(channel -> channel.getType().equals(ChannelType.VOICE))
                .map(channel -> (VoiceChannel)channel)
                .filter(channel -> channel.getName().equalsIgnoreCase("gunoi"))
                .findFirst().orElse(null);
        if (trash == null) return;

        event.getMessage().getMentionedMembers().forEach(
                member -> event.getGuild().moveVoiceMember(member, trash).completeAfter(1, TimeUnit.SECONDS)
        );
    }
}
