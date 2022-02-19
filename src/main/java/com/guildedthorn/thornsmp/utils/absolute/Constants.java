package com.guildedthorn.thornsmp.utils.absolute;

import com.guildedthorn.thornsmp.ThornSMP;

public final class Constants {
    public static final String BOT_TOKEN = ThornSMP.getInstance().getConfiguration().bot.token;
    public static final String BOT_BRIDGE_CHANNEL = ThornSMP.getInstance().getConfiguration().bot.bridgeChannel;
    
    public static final String SERVER_GUILD_ID = ThornSMP.getInstance().getConfiguration().server.guildId;
    public static final String SERVER_VOICE_CHANNEL_ID = ThornSMP.getInstance().getConfiguration().server.voiceChannelId;
    
    public static final String ELIXIR_ENDPOINT = ThornSMP.getInstance().getConfiguration().elixir.endpoint;
    public static final String ELIXIR_API_KEY = ThornSMP.getInstance().getConfiguration().elixir.apiKey;
}
