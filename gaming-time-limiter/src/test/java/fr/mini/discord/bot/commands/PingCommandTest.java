package fr.mini.discord.bot.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.InteractionApplicationCommandCallbackReplyMono;
import fr.mini.discord.bot.config.CommandNameEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PingCommandTest {

    @Autowired
    private PingCommand command;

    private ChatInputInteractionEvent mockedEvent;

    private InteractionApplicationCommandCallbackReplyMono mockedCallbackReply;

    @BeforeEach
    void setUp() {
        mockedEvent = mock(ChatInputInteractionEvent.class);
        mockedCallbackReply = mock(InteractionApplicationCommandCallbackReplyMono.class);
    }

    @Test
    void testGetNameIsCorrect() {
        assertEquals(CommandNameEnum.PING_COMMAND.getName(), command.getName());
    }

    @Test
    void testHandleIsCorrect() {
        when(mockedEvent.reply()).thenReturn(mockedCallbackReply);
        when(mockedCallbackReply.withEphemeral(anyBoolean())).thenReturn(mockedCallbackReply);
        command.handle(mockedEvent);
        verify(mockedEvent, times(1)).reply();
        verify(mockedCallbackReply, times(1)).withEphemeral(Boolean.TRUE);
        verify(mockedCallbackReply, times(1)).withContent("Pong!");
    }
}