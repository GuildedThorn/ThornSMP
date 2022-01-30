package com.guildedthorn.thornsmp.listeners;

import com.guildedthorn.thornsmp.item.backpack.BackpackInventory;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class ItemListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        
        ItemStack item = event.getItem();
        NBTItem nbtItem = new NBTItem(item);
        
        if(nbtItem.hasKey("backpackData")) {
            BackpackInventory.open(event.getPlayer(), item);
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        NBTItem nbtItem = new NBTItem(item);

        if(nbtItem.hasKey("backpackData")) {
            event.setCancelled(true);
        }
    }
}
