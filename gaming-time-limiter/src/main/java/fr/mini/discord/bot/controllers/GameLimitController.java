package fr.mini.discord.bot.controllers;

import com.austinv11.servicer.Service;
import discord4j.core.object.entity.User;
import org.springframework.core.task.TaskExecutor;

@Service
public class GameLimitController {

    private TaskExecutor taskExecutor;

    public GameLimitController(final TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public boolean limit(User userToLimit, long duration){
        return true;
    }
}
