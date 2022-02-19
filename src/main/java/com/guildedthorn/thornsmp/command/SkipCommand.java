package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.player.PlayerRequest;
import tech.xigam.elixirapi.requests.player.SkipRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class SkipCommand extends Command {
    public SkipCommand() {
        super("skip", "Skip to the next song in queue.", "/skip", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            PlayerRequest request = new SkipRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                if(response.getResponseCode() == 200) {
                    commandSender.sendMessage(colorize("&aSuccessfully skipped the song."));
                } else {
                    commandSender.sendMessage(colorize("&cUnable to skip the song."));
                }
            });
        } catch (RequestBuildException e) {
            commandSender.sendMessage(colorize("&cThere was an error while trying to skip the song."));
        }
        return true;
    }
}
