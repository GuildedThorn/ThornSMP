package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.requests.queue.QueueRequest;
import tech.xigam.elixirapi.requests.queue.ShuffleRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class ShuffleCommand extends Command {
    public ShuffleCommand() {
        super("shuffle", "Shuffles the queue.", "/shuffle", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            QueueRequest request = new ShuffleRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                if(response.getResponseCode() == 200) 
                    commandSender.sendMessage(colorize("&aSuccessfully shuffled the queue."));
                else commandSender.sendMessage(colorize("&cFailed to shuffle the queue."));
            });
        } catch (RequestBuildException ignored) {
            commandSender.sendMessage(colorize("&cThere was an error shuffling the queue."));
        }
        return true;
    }
}
