package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

@CommandDescriptor(acceptors = {"join"})
public class JoinVoiceChannel implements ICommand {

    @Override
    public void handle(GuildMessageReceivedEvent event)
    {
        System.out.println("[BOT] Executing JoinVoiceChannel Command");

        VoiceChannel voiceChannel = event.getGuild().getChannels().stream()
                .filter(gc -> gc.getType().equals(ChannelType.VOICE))
                .map(gc -> (VoiceChannel)gc)
                .filter(vc -> vc.getMembers().contains(event.getMember()))
                .findFirst()
                .orElse(null);

        if (voiceChannel == null)
        {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Lord Teemo cannot join")
                    .setDescription("You have to be in a voice channel to use this command.")
                    .setColor(0xc45c3f)
                    .build()).queue();
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
    }
}
