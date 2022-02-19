package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.objects.TrackObject;
import tech.xigam.elixirapi.requests.player.GetPlayingTrackRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class NowPlayingCommand extends Command {
    public NowPlayingCommand() {
        super("nowplaying", "Displays information about the currently playing song.", "/nowplaying", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            GetPlayingTrackRequest request = (GetPlayingTrackRequest) new GetPlayingTrackRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(res -> {
                GetPlayingTrackRequest.Response response = (GetPlayingTrackRequest.Response) res;
                if(!(response.getAsTrack() == null)) {
                    TrackObject track = response.getAsTrack();
                    commandSender.sendMessage(colorize("&d--------------------" + "\n" +
                            "&dNow Playing: &e" + track.title + "\n" +
                            "&dArtist: &e" + track.author + "\n" +
                            "&dDuration: &e" + track.length + "\n" +
                            "&d--------------------"));
                }
            });
        } catch (RequestBuildException ignored) { 
            commandSender.sendMessage(colorize("&cThere was an error while retrieving the currently playing song."));
        }
        return true;
    }
}
