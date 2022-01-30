package com.guildedthorn.thornsmp.listeners;

import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public final class WorldListener implements Listener {
    @EventHandler
    public void onBlockModify(EntityChangeBlockEvent event) {
        if(event.getEntity() instanceof Enderman)
            event.setCancelled(true);
    }
}
