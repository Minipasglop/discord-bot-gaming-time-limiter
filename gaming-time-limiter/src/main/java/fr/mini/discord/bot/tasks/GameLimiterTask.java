package fr.mini.discord.bot.tasks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLimiterTask implements Runnable {

    @Override
    public void run() {
        log.info("I'm a thread !");
    }
}
