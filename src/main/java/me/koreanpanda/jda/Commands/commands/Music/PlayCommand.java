package me.koreanpanda.jda.Commands.commands.Music;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import me.koreanpanda.jda.Music.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayCommand implements ICommand {
    private final YouTube youTube;

    public PlayCommand(){
        YouTube temp = null;

        try{
            temp = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null
            )
                    .setApplicationName("youtube bot")
                    .build();
        } catch(Exception e){
            e.printStackTrace();
        }

        youTube = temp;
    }

    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        Error error = new Error();
        if(args.isEmpty()){
            channel.sendMessage(error.Missing_Argument()).queue();
            return;
        }

        String input = String.join(" ", args);
        System.out.println(input);
        if(!isUrl(input)){
            String ytSearched = searchYoutube(input);
            System.out.println(ytSearched);
            if(ytSearched == null){
                channel.sendMessage("Youtube returned no results").queue();

                return;
            }
            input = ytSearched;
        }
        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlay(ctx.getChannel(), input);
    }

    private boolean isUrl(String input){
        try {
            new URL(input);

            return true;
        } catch (MalformedURLException ignored){
            return false;
        }
    }

    @Nullable
    private String searchYoutube(String input){
        try{
            List<SearchResult> results = youTube.search()
                    .list("id,snippet")
                    .setQ(input)
                    .setMaxResults(1L)
                    .setType("video")
                    .setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)")
                    .setKey(Config.get("youtubekey"))
                    .execute()
                    .getItems();

            if(!results.isEmpty()){
                String videoId = results.get(0).getId().getVideoId();

                return "https://www.youtube.com/watch?v=" + videoId;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: `" + Config.get("prefix") + getName() + " <song url>`";
    }
}
