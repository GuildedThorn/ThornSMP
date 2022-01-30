package com.guildedthorn.thornsmp.bot.commands;

import com.guildedthorn.thornsmp.ThornSMP;
import com.guildedthorn.thornsmp.utils.EmbedUtil;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Interaction;

public final class DeployCommand extends Command {
    public DeployCommand() {
        super("deploy", "Deploys slash-commands to this guild.");
    }

    @Override
    public void execute(Interaction interaction) {
        if(!interaction.getMember().getId().matches("654849939175768074")) {
            interaction.reply(EmbedUtil.getEmbed("You cannot deploy slash-commands."));
            return;
        }
        
        interaction.reply(EmbedUtil.getEmbed("Attempting to deploy slash-commands..."));
        ThornSMP.getInstance().getBot().getCommandHandler().deployAll(interaction.getGuild());
    }
}
