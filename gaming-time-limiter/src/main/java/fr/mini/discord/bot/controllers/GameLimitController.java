package fr.mini.discord.bot.controllers;

import discord4j.core.object.entity.User;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class GameLimitController {

    private TaskExecutor taskExecutor;

    public GameLimitController(final TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public boolean limit(User userToLimit, long duration) {
        return true;
    }
}
