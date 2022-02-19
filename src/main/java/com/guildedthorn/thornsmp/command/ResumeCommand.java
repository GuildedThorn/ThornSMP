package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.player.PlayerRequest;
import tech.xigam.elixirapi.requests.player.ResumeRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class ResumeCommand extends Command {
    public ResumeCommand() {
        super("resume", "Resumes the player.", "/resume", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            PlayerRequest request = new ResumeRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                if(response.getResponseCode() == 200) {
                    commandSender.sendMessage(colorize("&aSuccessfully resumed the player."));
                } else {
                    commandSender.sendMessage(colorize("&cUnable to resume the player."));
                }
            });
        } catch (RequestBuildException e) {
            commandSender.sendMessage(colorize("&cThere was an error while resuming the player."));
        }
        return true;
    }
}
