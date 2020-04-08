package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandDescriptor
public class UnhandledCommand implements ICommand {

    private EmbedBuilder response = new EmbedBuilder();
    private String value;

    public UnhandledCommand(String message)
    {
        value = message;
    }

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        System.out.println("[BOT] Executing Unhandled Command");

        response.setTitle("Error: Unhandled Command")
                .setDescription("The __Command Dispatcher__ tried to instantiate some wacky command. The impossible has happened!")
                .addField("Debugger Message:", value, true)
                .setColor(0xc45c3f);

        event.getChannel().sendMessage(response.build()).queue();
        response.clear();
    }
}
