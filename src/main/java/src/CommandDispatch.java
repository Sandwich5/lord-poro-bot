package src;

import commands.CommandBuilder;
import commands.ICommand;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import javax.annotation.Nonnull;

public class CommandDispatch extends ListenerAdapter
{
    CommandBuilder commandBuilder = new CommandBuilder();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] eventRawString = event.getMessage()
                .getContentRaw()
                .split("\\s+");

        if (!eventRawString[0].startsWith(Bot.prefix)) {roleSelector(event); return;}

        String issuedCommand = new StringBuilder(eventRawString[0])
                .substring(Bot.prefix.length());

        ICommand command = commandBuilder.requestCommand(issuedCommand).build();
        if (command != null)
            command.handle(event);
    }

    // @delete-me
    public void roleSelector(GuildMessageReceivedEvent event)
    {
        if (!event.getMessage().getContentRaw().equals("SelectRoles")) return;
        System.out.println("ROLE SELECTOR");
        event.getMessage().addReaction("U+1f916").queue();
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        Role memberRole = event.getGuild().getRoles().stream()
                .filter(role -> role.getName().equals("Member"))
                .findAny().orElse(null);
        if (memberRole == null) return;
        event.getGuild().addRoleToMember(event.getMember(), memberRole);
    }

    @Override
    public void onGenericGuildMessageReaction(@Nonnull GenericGuildMessageReactionEvent event) {
        Member member = event.getMember();
        if (member == null) return;
        if (event.getReaction().getReactionEmote().getAsCodepoints().equals("U+1f916"))
        {
            System.out.println("Selected ROBO-ROLE!");
            Role memberRole = event.getGuild().getRoles().stream()
                    .filter(role -> role.getName().equals("TEST_ROLE"))
                    .findAny().orElse(null);
            if (memberRole == null) return;
            if (member.getRoles().contains(memberRole)) return;
            event.getGuild().addRoleToMember(member, memberRole).queue();
        }
    }
}
