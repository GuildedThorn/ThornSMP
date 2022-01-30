package com.guildedthorn.thornsmp.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public final class EmbedUtil {
    public static MessageEmbed getEmbed(String description) {
        return new EmbedBuilder()
                .setDescription(description)
                .setColor(Color.RED)
                .build();
    }
}
