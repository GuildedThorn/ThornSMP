package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.ElixirAPI;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.playlist.PlaylistRequest;
import tech.xigam.elixirapi.requests.playlist.QueuePlaylistRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class PlaylistCommand extends Command {
    public PlaylistCommand() {
        super("playlist", "Does helpful things relating to Elixir playlists.", "/playlist <queue> <playlistId> [channelId]", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        if(args.length < 2) {
            commandSender.sendMessage(colorize("&cUsage: &7/playlist <queue> <playlistId> [channelId]"));
            return true;
        }

        PlaylistRequest.Builder builder;
        ElixirAPI elixirAPI = ThornSMP.getInstance().getElixirApi();
        switch(args[0].toLowerCase()) {
            default:
                commandSender.sendMessage(colorize("&cUsage: &7/playlist <queue> <playlistId> [channelId]"));
                return true;
            case "queue":
                builder = new QueuePlaylistRequest.Builder(elixirAPI)
                        .channel(Constants.SERVER_VOICE_CHANNEL_ID).playlist(args[1]);
                break;
        }
        
        try {
            builder.build().execute(response -> commandSender.sendMessage(colorize("&aExecution attempted.")));
        } catch (RequestBuildException ignored) {
            commandSender.sendMessage(colorize("&cFailed to execute."));
        } return true;
    }
}
