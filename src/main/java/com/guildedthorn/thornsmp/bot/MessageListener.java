package com.guildedthorn.thornsmp.bot;

import com.guildedthorn.thornsmp.utils.ChatUtil;
import com.guildedthorn.thornsmp.utils.absolute.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public final class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getChannel().getId().equals(Constants.BOT_BRIDGE_CHANNEL))
            return;
        if(event.isWebhookMessage() || event.getMember() == null || event.getMember().getUser().isBot())
            return;
        
        String message = event.getMessage().getContentStripped();
        ChatUtil.sendMessage(event.getMember().getEffectiveName(), message);
    }
}
