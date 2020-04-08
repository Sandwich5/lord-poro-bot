package commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.nio.channels.Channel;

@CommandDescriptor(acceptors = {"Wacky command delete me pls"})
public class IniateRoleSelectionChannel implements ICommand{

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        event.getGuild()
                .createTextChannel("role-select").complete()
                .sendMessage("Select your squad by interacting with the squad specific emote!").complete()
                .addReaction("U+1f916").complete();

        MessageBuilder messageBuilder = new MessageBuilder();
        for (Emote emote : event.getGuild().getEmotes())
            messageBuilder.append(emote.getName()).append(" | ");
        messageBuilder.append(" ; end of emotes");

        event.getGuild().getTextChannelsByName("role-select", true).get(0)
                .sendMessage(messageBuilder.build()).queue();
    }
}
