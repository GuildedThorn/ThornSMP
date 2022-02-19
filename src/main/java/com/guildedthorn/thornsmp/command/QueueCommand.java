package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tech.xigam.elixirapi.exceptions.RequestBuildException;
import tech.xigam.elixirapi.objects.TrackCollection;
import tech.xigam.elixirapi.objects.TrackObject;
import tech.xigam.elixirapi.requests.queue.GetQueueRequest;
import tech.xigam.elixirapi.requests.queue.QueueRequest;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class QueueCommand extends Command {
    public QueueCommand() {
        super("queue", "Gets the next 5 songs in the queue.", "/queue", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args) {
        try {
            QueueRequest request = new GetQueueRequest.Builder(ThornSMP.getInstance().getElixirApi())
                    .guild(Constants.SERVER_GUILD_ID).build();
            request.execute(response -> {
                commandSender.sendMessage(colorize("&d--------------------"));
                TrackCollection tracks = response.getAsTrackCollection();
                for (int i = 1; i <= Math.min(5, tracks.tracks.size()); i++) {
                    TrackObject track = tracks.tracks.get(i);
                    commandSender.sendMessage(colorize(String.format("&f&l#%s &r&dTitle: &f" + track.title + "&d - Artist: &f" + track.author, i)));
                }
                commandSender.sendMessage(colorize("&d--------------------"));
            });
        } catch (RequestBuildException ignored) {
            commandSender.sendMessage(colorize("&cThere was an error getting the queue."));
        }
        return true;
    }
}
