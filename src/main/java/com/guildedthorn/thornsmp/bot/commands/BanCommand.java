package com.guildedthorn.thornsmp.bot.commands;

import com.guildedthorn.thornsmp.utils.EmbedUtil;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BanCommand extends Command implements Arguments {

    public BanCommand() {
        super("ban", "Ban an online player from the smp.");
    }

    @Override
    public void execute(Interaction interaction) {
        if(interaction.getMember().getId().matches("654849939175768074")) {
            Player player = Bukkit.getPlayer(interaction.getArgument("name", String.class));
            if (player != null) {
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), interaction.getArgument("reason", "You were banned by admin.", String.class), new Date(System.currentTimeMillis()+60*60*1000), null);
                interaction.reply(EmbedUtil.getEmbed("Successfully Banned the player."));
            } else {
                interaction.reply(EmbedUtil.getEmbed("The player is not online."));
            }
        } else {
            interaction.reply(EmbedUtil.getEmbed("You do not have permissions to do this."));
        }
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("name", "The name of the player you want to ban.", "player", OptionType.STRING, true, 0),
                Argument.create("reason", "The reason you want to ban the player.", "reason", OptionType.STRING, false, 1)
        );
    }
    
}
