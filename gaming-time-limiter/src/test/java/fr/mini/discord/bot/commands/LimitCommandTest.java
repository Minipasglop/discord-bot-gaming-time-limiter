package fr.mini.discord.bot.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.User;
import discord4j.core.spec.InteractionApplicationCommandCallbackReplyMono;
import fr.mini.discord.bot.config.CommandNameEnum;
import fr.mini.discord.bot.controllers.GameLimitController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@SpringBootTest
class LimitCommandTest {

    @Mock
    private GameLimitController mockedGameLimitController;

    @InjectMocks
    private LimitCommand command;

    private ChatInputInteractionEvent mockedEvent;

    private InteractionApplicationCommandCallbackReplyMono mockedCallbackReply;

    @BeforeEach
    void setUp() {
        mockedEvent = mock(ChatInputInteractionEvent.class);
        mockedCallbackReply = mock(InteractionApplicationCommandCallbackReplyMono.class);
    }

    @Test
    void testGetNameIsCorrect() {
        assertEquals(CommandNameEnum.LIMIT_COMMAND.getName(), command.getName());
    }

    @Test
    void testHandleIsCorrect() {
        // TODO Create Option item builder for tests
        when(mockedEvent.reply()).thenReturn(mockedCallbackReply);
        when(mockedCallbackReply.withEphemeral(anyBoolean())).thenReturn(mockedCallbackReply);
        when(mockedGameLimitController.limit(any(User.class), anyLong())).thenReturn(Boolean.TRUE);
        command.handle(mockedEvent);
        verify(mockedEvent, times(1)).reply();
        verify(mockedCallbackReply, times(1)).withEphemeral(Boolean.TRUE);
        verify(mockedCallbackReply, times(1)).withContent("Duration of 10 minutes noted!");
    }

    @Test
    void testHandleIsNotCorrect() {
        // TODO Create Option item builder for tests
        when(mockedEvent.reply()).thenReturn(mockedCallbackReply);
        when(mockedCallbackReply.withEphemeral(anyBoolean())).thenReturn(mockedCallbackReply);
        when(mockedGameLimitController.limit(any(User.class), anyLong())).thenReturn(Boolean.FALSE);
        command.handle(mockedEvent);
        verify(mockedEvent, times(1)).reply();
        verify(mockedCallbackReply, times(1)).withEphemeral(Boolean.TRUE);
        verify(mockedCallbackReply, times(1)).withContent("An error occured.");
    }

}