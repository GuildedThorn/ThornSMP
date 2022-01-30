package com.guildedthorn.thornsmp.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Interaction;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Collection;

public final class ListCommand extends Command {

    public ListCommand() {
        super("list", "List all the online players.");
    }
    
    @Override
    public void execute(Interaction interaction) {
        interaction.deferReply();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        EmbedBuilder builder = new EmbedBuilder(); StringBuilder playerList = new StringBuilder();
        
        builder.setTitle("Online Players");
        builder.setTimestamp(OffsetDateTime.now());
        builder.setColor(Color.RED);
        
        if(players.size() <= 0)
            builder.setDescription("There are no players online.");
        else {
            int count = 0;
            for(Player player : players) {
                if(count < 3) {
                    playerList.append(player.getName() + " ");
                    count++;
                } else {
                    playerList.append("\n" + player.getName() + " ");
                    count = 0;
                }
            } builder.setDescription(playerList.toString());
        }
        
        interaction.reply(builder.build());
    }
}
