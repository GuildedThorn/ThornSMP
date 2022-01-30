package com.guildedthorn.thornsmp.bot.commands;

import com.guildedthorn.thornsmp.utils.EmbedUtil;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Interaction;

public final class TPSCommand extends Command {
    public TPSCommand() {
        super("tps", "Check the server's current TPS.");
    }
    
    @Override
    public void execute(Interaction interaction) {
        long tps = Math.round(
                ((CraftServer) Bukkit.getServer()).getServer().recentTps[0]
        );
        interaction.reply(EmbedUtil.getEmbed("The server TPS is: " + tps));
    }
}
