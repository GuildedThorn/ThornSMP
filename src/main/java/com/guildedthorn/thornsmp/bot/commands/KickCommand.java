package com.guildedthorn.thornsmp.bot.commands;

import com.guildedthorn.thornsmp.utils.EmbedUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import java.util.Collection;
import java.util.List;

public final class KickCommand extends Command implements Arguments {
    public KickCommand() {
        super("kick", "Kick an online player from the server.",
                "What is the name of the **online** player you want to kick?",
                "Why do you want to kick this player?");
    }

    @Override
    public void execute(Interaction interaction) {
        if(!interaction.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            interaction.reply(EmbedUtil.getEmbed("You do not have permission to do this."));
            return;
        }

        Player player = Bukkit.getPlayer(interaction.getArgument("name", String.class));
        if (player != null) {
            player.kickPlayer(interaction.getArgument("reason", "Kicked by admin.",  String.class));
            interaction.reply(EmbedUtil.getEmbed("Successfully kicked the player."));
        } else {
            interaction.reply(EmbedUtil.getEmbed("The player is not online."));
        }
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("name", "The name of the player you want to kick.", "player", OptionType.STRING, true, 0),
                Argument.create("reason", "The reason you want to kick the player.", "reason", OptionType.STRING, false, 1)
        );
    }
}
