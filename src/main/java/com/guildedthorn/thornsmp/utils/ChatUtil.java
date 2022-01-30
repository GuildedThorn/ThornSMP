package com.guildedthorn.thornsmp.utils;

import org.bukkit.Bukkit;

public final class ChatUtil {
    public static void sendMessage(String sender, String message) {
        Bukkit.getServer().broadcastMessage(
                "<" + sender + "> " + message
        );
    }
}
