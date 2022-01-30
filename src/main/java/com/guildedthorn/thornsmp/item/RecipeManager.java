package com.guildedthorn.thornsmp.item;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.item.backpack.BackpackInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public final class RecipeManager {
    public static void addAllRecipes() {
        RecipeManager.addBackpackRecipe();
    }
    
    public static void addBackpackRecipe() {
        NamespacedKey key = new NamespacedKey(ThornSMP.getInstance(), "backpack");
        ShapedRecipe recipe = new ShapedRecipe(key, BackpackInventory.create());
        recipe.shape("#L#", "SCS", "LLL")
                .setIngredient('#', Material.AIR)
                .setIngredient('L', Material.LEATHER)
                .setIngredient('S', Material.STRING)
                .setIngredient('C', Material.TRAPPED_CHEST);
        Bukkit.addRecipe(recipe);
    }
}
