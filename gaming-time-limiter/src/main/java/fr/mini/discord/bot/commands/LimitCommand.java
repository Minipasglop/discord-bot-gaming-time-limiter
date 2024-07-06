package fr.mini.discord.bot.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import fr.mini.discord.bot.config.CommandNameEnum;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LimitCommand implements SlashCommand {

    @Override
    public String getName() {
        return CommandNameEnum.LIMIT_COMMAND.getName();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long duration = event.getOption("duration")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .get();
        // We reply the command has been received and the timer begins based on the user presence.
        return event.reply()
                .withEphemeral(true)
                .withContent("Duration of %s minutes noted!".formatted(duration));
    }
}
