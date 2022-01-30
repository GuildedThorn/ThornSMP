package com.guildedthorn.thornsmp.item.backpack;

import com.google.gson.Gson;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.mrmicky.fastinv.FastInv;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class BackpackInventory extends FastInv {
    private final BackpackInventory.Data backpackData;
    public final EquipmentSlot slotWithItem;
    public final int slotId;
    
    @SuppressWarnings("ConstantConditions")
    public BackpackInventory(Player player, ItemStack backpack, Data backpackData) {
        super(9, backpack.getItemMeta().hasDisplayName() ? backpack.getItemMeta().getDisplayName() : "Backpack"); this.backpackData = backpackData;
        this.slotWithItem = player.getInventory().getItemInMainHand()
                .getType() == Material.CHEST ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
        this.slotId = player.getInventory().getHeldItemSlot();
        
        for(int i = 0; i < 9; i++) {
            this.setItem(i, ItemStack.deserialize(backpackData.inventory.get(i)));
        }
        
        this.addClickHandler(event -> {
            ItemStack clickedItem = event.getCursor();
            if(clickedItem != null)
                if(!clickedItem.getType().equals(Material.CHEST))
                    event.setCancelled(false);
        });
        this.addCloseHandler(event -> BackpackInventory.save(player, backpack, this));
    }
    
    public BackpackInventory.Data getBackpackData() {
        return this.backpackData;
    }

    public static ItemStack create() {
        ItemStack backpack = new ItemStack(Material.CHEST);
        BackpackInventory.Data backpackData = new Data();
        NBTItem backpackNbt = new NBTItem(backpack, true);
        
        Map<String, Object> airBlock = new ItemStack(Material.AIR).serialize();
        backpackData.inventory = new HashMap<>(Map.of(
                0, airBlock,
                1, airBlock,
                2, airBlock,
                3, airBlock,
                4, airBlock,
                5, airBlock,
                6, airBlock,
                7, airBlock,
                8, airBlock
        ));
        
        NBTCompound dataTag = backpackNbt.addCompound("backpackData");
        dataTag.setString("data", backpackData.createData());
        backpackNbt.setString("uuid", UUID.randomUUID().toString());

        ItemMeta itemMeta = backpack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(colorize("&cBackpack"));
            backpack.setItemMeta(itemMeta);
        }
        
        return backpack;
    }
    
    public static void open(Player player, ItemStack backpack) {
        if(!backpack.getType().equals(Material.CHEST))
            return;
        
        NBTItem backpackNbt = new NBTItem(backpack, true);
        if(!backpackNbt.hasKey("backpackData"))
            return;
        
        BackpackInventory.Data backpackData = new Data();
        backpackData.fromData(backpackNbt.getCompound("backpackData").getString("data"));
        
        BackpackInventory backpackInventory = new BackpackInventory(player, backpack, backpackData);
        backpackInventory.open(player);
    }
    
    public static void save(Player player, ItemStack backpack, BackpackInventory inventory) {
        if(!backpack.getType().equals(Material.CHEST))
            return;

        NBTItem backpackNbt = new NBTItem(backpack, true);
        if(!backpackNbt.hasKey("backpackData"))
            return;

        BackpackInventory.Data backpackData = inventory.getBackpackData();
        for(int i = 0; i < 9; i++) {
            ItemStack itemToWrite = inventory.getInventory().getItem(i);
            if(itemToWrite == null)
                itemToWrite = new ItemStack(Material.AIR);
            backpackData.inventory.put(i, itemToWrite.serialize());
        }
        
        backpackNbt.getCompound("backpackData")
                .setString("data", backpackData.createData());
        if(inventory.slotWithItem == EquipmentSlot.HAND)
            player.getInventory().setItem(inventory.slotId, backpack.clone());
        else player.getInventory().setItemInOffHand(backpack.clone());
    }
    
    public static class Data {
        public Map<Integer, Map<String, Object>> inventory;
        
        public String createData() {
            return new Gson().toJson(this);
        }
        
        public void fromData(String data) {
            this.inventory = new Gson().fromJson(data, Data.class).inventory;
        }
    }
}
