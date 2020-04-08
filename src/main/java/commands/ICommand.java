package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface ICommand {
    void handle(GuildMessageReceivedEvent event);
}
