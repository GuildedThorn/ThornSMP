package com.guildedthorn.thornsmp;

import com.google.gson.Gson;
import com.guildedthorn.thornsmp.bot.SMPBot;
import com.guildedthorn.thornsmp.item.RecipeManager;
import com.guildedthorn.thornsmp.player.SMPPlayer;
import com.guildedthorn.thornsmp.utils.Config;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.kingrainbow44.crafttools.plugin.ExtendedPlugin;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class ThornSMP extends ExtendedPlugin {
    private static ThornSMP instance;
    
    private BossBar bossBar;
    private Config config = new Config();
    private SMPBot bot;
    
    public static ThornSMP getInstance() {
        return instance;
    }

    /*
     * Data Methods
     */
    
    public BossBar getBossBar() {
        return this.bossBar;
    }
    
    public Config getConfiguration() {
        return this.config;
    }
    
    public SMPBot getBot() {
        return this.bot;
    }

    /*
     * Plugin Methods.
     */

    @Override
    protected void init() {
        instance = this;

        try {
            File file = new File(getDataFolder(), "config.json");
            if(!file.exists()) {
                if(!file.createNewFile())
                    getLogger().severe("Unable to create config file.");
            }
            
            this.config = new Gson().fromJson(new FileReader(file), Config.class);
        } catch (IOException ignored) { }
        
        CraftPlayerManager.setPlayerClass(SMPPlayer.class);
    }

    @Override
    protected void enable() {
        this.bossBar = Bukkit.createBossBar(colorize("&cThornSMP"), BarColor.RED, BarStyle.SOLID);
        if(!this.config.bot.token.isEmpty()) {
            this.bot = SMPBot.initialize(this.config.bot.token);
        }
        FastInvManager.register(this);
        RecipeManager.addAllRecipes();
        
        getLogger().info("ThornSMP has been enabled!");
    }

    @Override
    protected void disable() {
        getLogger().info("ThornSMP has been disabled!");
    }
    
    /*
     * Internal Methods
     */

    @Override
    protected String getCommandPath() {
        return "com.guildedthorn.thornsmp.command";
    }

    @Override
    protected String getListenerPath() {
        return "com.guildedthorn.thornsmp.listeners";
    }
}
