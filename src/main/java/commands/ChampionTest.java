package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.constant.Platform;

@CommandDescriptor(acceptors = {"champ"})
public class ChampionTest implements ICommand {
    @Override
    public void handle(GuildMessageReceivedEvent event) {
        Champion c = null;
        try {
            ApiConfig config = new ApiConfig().setKey("RGAPI-02c5b486-61b5-453f-8589-4a8d94456eb1");
            RiotApi api = new RiotApi(config);
            c = api.getDataChampion(Platform.EUNE, 1);
        } catch (RiotApiException e) {
            e.printStackTrace();
            new UnhandledCommand("Riot API returned 403: Forbidden").handle(event);
            return;
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Champion")
                .addField("Name: ", c.getName(), true)
                .addField("Title: ", c.getTitle(), true)
                .setThumbnail("http://ddragon.leagueoflegends.com/cdn/10.7.1/img/champion/" + c.getName() + ".png");
        event.getChannel().sendMessage(eb.build()).complete();
    }
}
