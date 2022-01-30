package com.guildedthorn.thornsmp.bot;

import com.guildedthorn.thornsmp.bot.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.Nullable;
import tech.xigam.cch.ComplexCommandHandler;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public final class SMPBot {
    @Nullable public static SMPBot initialize(String token) {
        try {
            return new SMPBot(token);
        } catch (LoginException ignored) { }
        
        return null;
    }
    
    private final JDA jda;
    private final ComplexCommandHandler commandHandler = new ComplexCommandHandler(true);
    
    private SMPBot(String token) throws LoginException {
        commandHandler.setPrefix("t!")
                .registerCommand(new MessageCommand())
                .registerCommand(new TPSCommand())
                .registerCommand(new DeployCommand())
                .registerCommand(new KickCommand())
                .registerCommand(new BanCommand())
                .registerCommand(new ListCommand());
        this.jda = JDABuilder.create(token, EnumSet.allOf(GatewayIntent.class))
                .setAutoReconnect(true)
                .setActivity(Activity.listening("to the SMP chat"))
                .addEventListeners(
                        new MessageListener(),
                        commandHandler
                ).build();
        commandHandler.setJda(this.jda);
    }
    
    public void sendMessage(String channelId, String sender, String message) {
        TextChannel channel = this.jda.getTextChannelById(channelId);
        if(channel == null) return;
        
        channel.sendMessage("<" + sender + "> " + message).queue();
    }
    
    public ComplexCommandHandler getCommandHandler() {
        return this.commandHandler;
    }
    
    public JDA getJda() {
        return this.jda;
    }
}
