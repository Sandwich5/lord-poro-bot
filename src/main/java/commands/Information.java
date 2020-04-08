package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandDescriptor(acceptors = {"info", "information"})
public class Information implements ICommand {

    private EmbedBuilder response = new EmbedBuilder();

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        System.out.println("[BOT] Executing Information Command");

        // sandwich5 user-id
        User sandwich5 = event.getJDA().retrieveUserById("278926678841884672").complete();

        response.setThumbnail("https://i.imgur.com/ipUNux7.jpg")
                .setTitle(":information_source: Lord-Teemo: Discord Bot")
                .setDescription("Development-Test pre-Alpha version 2.0_SNAPSHOT")
                .addField("Visit JDA library", "https://github.com/DV8FromTheWorld/JDA", false)
                .addField("Visit LAVA library", "https://github.com/sedmelluq/lavaplayer", false)
                .addField("Check out JetBrains", "https://www.jetbrains.com/", false)
                .setColor(0xc45c3f)
                .setFooter("Created by sandwich5", sandwich5.getAvatarUrl());

        event.getChannel().sendMessage(response.build()).queue();
        response.clear();
    }
}
