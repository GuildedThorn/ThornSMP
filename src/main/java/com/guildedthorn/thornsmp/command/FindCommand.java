package com.guildedthorn.thornsmp.command;

import com.guildedthorn.thornsmp.player.SMPPlayer;
import com.guildedthorn.thornsmp.utils.FindUtil;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class FindCommand extends Command {
    private static final Map<Player, Player> allowed = new HashMap<>();
    
    public FindCommand() {
        super("find", "Find a player in your dimension.", "/find <player|remove>", Collections.emptyList());
    }
    
    @SuppressWarnings("ConstantConditions") @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if(allowed.containsKey(player)) {
            Player target = allowed.get(player);
            FindUtil.showLocation(target, player);
            player.sendMessage(colorize("&aYour location was sent to " + target.getDisplayName() + "."));
            
            allowed.remove(player);
            return true;
        }
        
        if(args.length < 1) {
            sender.sendMessage(colorize("&cProvide a player to find. (or use '/find remove')"));
            return true;
        }
        
        if(args[0].equalsIgnoreCase("remove")) {
            LCWaypoint waypoint = CraftPlayerManager.self().getCraftPlayer(player, SMPPlayer.class).getWaypoint();
            if(waypoint == null) {
                player.sendMessage(colorize("&cYou don't have a waypoint set."));
                return true;
            }
            
            LunarClientAPI.getInstance().removeWaypoint(player, waypoint); 
            player.sendMessage(colorize("&aThe waypoint has been removed.")); return true;
        }
        
        if(!allowed.containsValue(player)) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                sender.sendMessage(colorize("&cPlayer not found."));
                return true;
            }
            
            if(target.getDisplayName().matches(player.getDisplayName())) {
                sender.sendMessage(colorize("&cYou can't find yourself."));
                return true;
            }
            
            allowed.put(target, player);
            player.sendMessage(colorize("&aSent a location request to " + target.getDisplayName() + "."));
            target.sendMessage(colorize("&a" + player.getDisplayName() + " is requesting your location. Do /find to accept."));
        } else {
            player.sendMessage(colorize("&cYou've already requested a location!"));
        } return true;
    }
}
