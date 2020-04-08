package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.ChampionInfo;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.List;

@CommandDescriptor(acceptors = {"summ"})
public class SummonerStats implements ICommand {
    @Override
    public void handle(GuildMessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split("\\s+");

        for (String s : command) System.out.println(s);

        Summoner summoner = null;

        try {
            ApiConfig config = new ApiConfig().setKey("RGAPI-02c5b486-61b5-453f-8589-4a8d94456eb1");
            RiotApi api = new RiotApi(config);
            summoner = api.getSummonerByName(Platform.EUNE, command[1]);
        } catch (RiotApiException e) {
            e.printStackTrace();
            return;
        }

        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Summoner")
                .addField("Name: ", summoner.getName(), false)
                .addField("Level: ", "" + summoner.getSummonerLevel(), false)
                .setThumbnail("http://ddragon.leagueoflegends.com/cdn/10.7.1/img/profileicon/" + summoner.getProfileIconId() + ".png");

        event.getChannel().sendMessage(builder.build()).complete();
        builder.clear();
    }

}

class RiotAPIWrapper
{
    private static RiotAPIWrapper single_instance = null;
    public RiotApi api;

    private RiotAPIWrapper()
    {
        ApiConfig config = new ApiConfig().setKey("RGAPI-491c144c-d901-42de-852d-410ae6bed679");
        RiotApi api = new RiotApi(config);
    }

    public static RiotAPIWrapper getInstance()
    {
        if (single_instance == null)
            single_instance = new RiotAPIWrapper();

        return single_instance;
    }
}