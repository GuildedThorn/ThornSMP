package com.guildedthorn.thornsmp.listeners;

import com.guildedthorn.thornsmp.player.SMPPlayer;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        SMPPlayer smpPlayer = CraftPlayerManager.self().getCraftPlayer(player, SMPPlayer.class);
        
        if(smpPlayer.getWaypoint() != null) {
            LunarClientAPI.getInstance().removeWaypoint(player, smpPlayer.getWaypoint());
        }
    }
}
