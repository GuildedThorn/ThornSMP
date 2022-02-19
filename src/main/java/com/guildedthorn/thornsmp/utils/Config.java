package com.guildedthorn.thornsmp.utils;

public final class Config {
    public Bot bot;
    public Server server;
    public Elixir elixir;
    
    public static class Bot {
        public String token = "", bridgeChannel = "936656719977791588";
    }
    
    public static class Server {
        public String guildId = "887516061266755585", voiceChannelId = "887526088811618366";
    }
    
    public static class Elixir {
        public String endpoint = "https://app.ponjo.club/v1/elixir/", apiKey = "";
    }
}
