package fr.mini.discord.bot.utils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.discordjson.json.ApplicationCommandInteractionOptionData;

import java.util.Optional;

public class EventOptionBuilder {

    public static Optional<ApplicationCommandInteractionOption> buildOptionLong(GatewayDiscordClient gateway, String key, long value, int optionType) {
        ApplicationCommandInteractionOptionData commandInteractionData = ApplicationCommandInteractionOptionData.builder().name(key).value(String.valueOf(value)).type(optionType).build();
        ApplicationCommandInteractionOption commandInteractionOption = new ApplicationCommandInteractionOption(gateway, commandInteractionData, null, null);
        return Optional.of(commandInteractionOption);
    }
}
