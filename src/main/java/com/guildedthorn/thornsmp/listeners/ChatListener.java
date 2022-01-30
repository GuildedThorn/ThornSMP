package com.guildedthorn.thornsmp.listeners;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.bot.SMPBot;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        SMPBot bot = ThornSMP.getInstance().getBot();
        
        bot.sendMessage(Constants.BOT_BRIDGE_CHANNEL, event.getPlayer().getDisplayName(), message);
    }
}
