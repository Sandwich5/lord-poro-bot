package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

@CommandDescriptor(acceptors = {"leave"})
public class LeaveVoiceChannel implements ICommand {

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.closeAudioConnection();
    }
}
