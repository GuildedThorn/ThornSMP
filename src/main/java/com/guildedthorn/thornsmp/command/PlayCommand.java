package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.player.PlayRequest;
import tech.xigam.elixirapi.requests.player.PlayerRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class PlayCommand extends Command {
    public PlayCommand() {
        super("play", "Queues a song to be played.", "/play <song name/url>", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        if(args.length < 1) {
            commandSender.sendMessage(colorize("&cUsage: &7/play <song name/url>"));
            return true;
        }
        
        String query = String.join(" ", args);
        try {
            PlayerRequest request = new PlayRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .track(query).guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                if(response.getResponseCode() == 200) {
                    commandSender.sendMessage(colorize("&aSuccessfully queued &7" + query + "&a."));
                } else {
                    commandSender.sendMessage(colorize("&cFailed to queue &7" + query + "&c."));
                }
            });
        } catch (RequestBuildException ignored) {
            commandSender.sendMessage(colorize("&cThere was an error while queuing the song."));
        }
        return true;
    }
}
