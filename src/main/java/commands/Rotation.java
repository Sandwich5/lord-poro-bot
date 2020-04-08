package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.ChampionInfo;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@CommandDescriptor(acceptors = {"rot"})
public class Rotation implements ICommand {

    @Override
    public void handle(GuildMessageReceivedEvent event) {
        ChampionInfo rotation = null;
        ChampionList championList = null;
        try {
            ApiConfig config = new ApiConfig().setKey("RGAPI-02c5b486-61b5-453f-8589-4a8d94456eb1");
            RiotApi api = new RiotApi(config);
            //rotation = api.getChampionRotations(Platform.EUNE);
            Thread.sleep(100);
            championList = api.getDataChampionList(Platform.EUNE, null, null, false, ChampionListTags.ALL);
        } catch (RiotApiException | InterruptedException e) {
            e.printStackTrace();
            new UnhandledCommand("Riot API returned 403: Forbidden").handle(event);
            return;
        }

//        if (rotation == null || championList == null) return;
//
//        EmbedBuilder builder = new EmbedBuilder()
//                .setTitle("Free Champion Rotation");
//        ChampionInfo finalRotation = rotation;
//        championList.getData().forEach((key, champion) -> {
//            if (finalRotation.getFreeChampionIds().contains(champion.getId()))
//            {
//                builder.addField(champion.getName(), champion.getTitle(), false);
//            }
//        });
//
//        event.getChannel().sendMessage(builder.build()).complete();
//        builder.clear();
    }
}
