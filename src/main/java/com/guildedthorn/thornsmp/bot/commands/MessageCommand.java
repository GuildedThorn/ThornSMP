package com.guildedthorn.thornsmp.bot.commands;

import com.guildedthorn.thornsmp.utils.EmbedUtil;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import java.util.Collection;
import java.util.List;

import static com.kingrainbow44.crafttools.utils.CraftUtils.*;

public final class MessageCommand extends Command implements Arguments {
    public MessageCommand() {
        super("message", "Send messages to online members.");
    }

    @Override
    public void execute(Interaction interaction) {
        interaction.setEphemeral().deferReply();
        
        String playerUsername = interaction.getArgument("player", String.class);
        String message = interaction.getArgument("message", String.class);

        Player player = Bukkit.getPlayer(playerUsername);
        if(player == null) {
            interaction.reply(EmbedUtil.getEmbed("That player isn't online!"));
            return;
        }
        
        player.sendMessage(colorize("&7&o" + interaction.getMember().getEffectiveName() + " whispers to you: " + message));
        interaction.reply(EmbedUtil.getEmbed("The message was sent to the player!"));
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("player", "The player to send a message to.", "player", OptionType.STRING, true, 0),
                Argument.create("message", "The message to send.", "message", OptionType.STRING, true, 1)
        );
    }
}
