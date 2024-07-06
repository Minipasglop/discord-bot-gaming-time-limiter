package fr.mini.discord.bot.config;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandNameEnum {

    PING_COMMAND("ping");

    private String name;

}
