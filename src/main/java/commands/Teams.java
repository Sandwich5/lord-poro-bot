package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.*;

@CommandDescriptor(acceptors = {"team"})
public class Teams implements ICommand {
    @Override
    public void handle(GuildMessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split("\\s+");
        if (command.length == 1)
        {
            handle1(event);
        }
        else if (command[1].equals("-move"))
        {
            System.out.println("--move");
            handle2(event);
        }
    }

    protected void handle1(GuildMessageReceivedEvent event)
    {
        VoiceChannel currentChannel = event.getGuild().getVoiceChannels().stream()
                .filter(voiceChannel -> voiceChannel.getMembers().contains(event.getMember()))
                .findFirst().orElse(null);
        if (currentChannel == null) {
            System.out.println("Could not find a current voice channel");
            return;
        }

        List<Member> memberList = new ArrayList<>(currentChannel.getMembers());
        System.out.println("Total entries: " + memberList.size());
        Collections.shuffle(memberList);
        EmbedBuilder embedBuilder = new EmbedBuilder().setColor(0xc45c3f);
        StringBuilder team = new StringBuilder();
        int i;
        for (i = 0; i < memberList.size() / 2; ++i)
        {
            team.append(nonNullName(memberList.get(i))).append("\n");
            System.out.println("Team 1 : " + nonNullName(memberList.get(i)) + " index: " + i);
        }
        embedBuilder.setTitle("Random Teams")
                .addField("Team 1:", team.toString(), false);
        team = new StringBuilder();
        for (; i < memberList.size(); ++i)
        {
            team.append(nonNullName(memberList.get(i))).append("\n");
            System.out.println("Team 1 : " + nonNullName(memberList.get(i)) + " index: " + i);
        }
        embedBuilder.setTitle("Random Teams")
                .addField("Team 2:", team.toString(), false);

        event.getChannel().sendMessage(embedBuilder.setThumbnail("https://i.imgur.com/X1znoTY.png").build()).complete();
    }

    protected void handle2(GuildMessageReceivedEvent event)
    {
        VoiceChannel currentChannel = event.getGuild().getVoiceChannels().stream()
                .filter(voiceChannel -> voiceChannel.getMembers().contains(event.getMember()))
                .findFirst().orElse(null);
        VoiceChannel lol_1 = event.getGuild().getVoiceChannelsByName("lol 1", true).get(0);
        VoiceChannel lol_2 = event.getGuild().getVoiceChannelsByName("lol 2", true).get(0);

        if (currentChannel == null) {
            System.out.println("Could not find a current voice channel");
            return;
        }
        if (lol_1 == null) {
            System.out.println("Could not find LOL1 voice channel");
            return;
        }
        if (lol_2 == null) {
            System.out.println("Could not find LOL2 voice channel");
            return;
        }

        StringBuilder team1 = new StringBuilder();
        StringBuilder team2 = new StringBuilder();
        List<Member> members = new ArrayList<Member>(currentChannel.getMembers());
        List<Member> team1_Members = new ArrayList<>();
        List<Member> team2_Members = new ArrayList<>();
        for (int i = 0; i < members.size(); ++i)
        {
            if (i % 2 == 0)
            {
                team1.append(nonNullName(members.get(i))).append("\n");
                team1_Members.add(members.get(i));
            }
            else
            {
                team2.append(nonNullName(members.get(i))).append("\n");
                team2_Members.add(members.get(i));
            }
        }

        event.getChannel().sendMessage(new EmbedBuilder()
            .setTitle("Random Teams")
            .addField("Team 1: ", team1.toString(), false)
            .addField("Team 2: ", team2.toString(), false)
            .setColor(0xc45c3f)
            .setThumbnail("https://i.imgur.com/X1znoTY.png")
            .build()).complete();

        team1_Members.forEach(member -> event.getGuild().moveVoiceMember(member, lol_1).queue());
        team2_Members.forEach(member -> event.getGuild().moveVoiceMember(member, lol_2).queue());
    }

    protected String nonNullName(Member member)
    {
        String bot = member.getUser().isBot() ? " `BOT`" : "";
        if (member.getNickname() != null) return member.getNickname() + bot;
        return member.getEffectiveName() + bot;
    }

    class MyUser {
        public MyUser(String n, long sf, double sc) {
            name = n;
            snowflake = sf;
            score = sc;
        }
        public String name;
        public long snowflake;
        public double score;
    }

    static Set<MyUser> users = new HashSet<MyUser>();

    public Teams()
    {
        users.add(new MyUser("iusti", 0, 3.407272727));
        users.add(new MyUser("dani", 0, 3.18));
        users.add(new MyUser("denisa", 0, 2.727272727));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
        users.add(new MyUser("iusti", 0, 0));
    }
}
