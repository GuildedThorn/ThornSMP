package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.player.PauseRequest;
import tech.xigam.elixirapi.requests.player.PlayerRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class PauseCommand extends Command {
    public PauseCommand() {
        super("pause", "Pauses the player.", "/pause", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            PlayerRequest request = new PauseRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                if(response.getResponseCode() == 200) {
                    commandSender.sendMessage(colorize("&aSuccessfully paused the player."));
                } else {
                    commandSender.sendMessage(colorize("&cUnable to pause the player."));
                }
            });
        } catch (RequestBuildException e) {
            commandSender.sendMessage(colorize("&cThere was an error while pausing the player."));
        }
        return true;
    }
}
