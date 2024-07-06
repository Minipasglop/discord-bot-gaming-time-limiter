package fr.mini.discord.bot.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.Interaction;
import discord4j.core.object.entity.User;
import discord4j.core.spec.InteractionApplicationCommandCallbackReplyMono;
import discord4j.discordjson.json.UserData;
import fr.mini.discord.bot.config.CommandNameEnum;
import fr.mini.discord.bot.controllers.GameLimitController;
import fr.mini.discord.bot.utils.EventOptionBuilder;
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

    public static final String DURATION_OPTION_NAME = "duration";
    public static final int INTEGER_PARAMETER_TYPE = 4;
    @Mock
    private GameLimitController mockedGameLimitController;

    @InjectMocks
    private LimitCommand command;

    private ChatInputInteractionEvent mockedEvent;

    private InteractionApplicationCommandCallbackReplyMono mockedCallbackReply;

    private Interaction mockedInteraction;

    private GatewayDiscordClient mockedGateway;

    @BeforeEach
    void setUp() {
        mockedEvent = mock(ChatInputInteractionEvent.class);
        mockedCallbackReply = mock(InteractionApplicationCommandCallbackReplyMono.class);
        mockedInteraction = mock(Interaction.class);
        mockedGateway = mock(GatewayDiscordClient.class);
        when(mockedEvent.getOption(DURATION_OPTION_NAME)).thenReturn(EventOptionBuilder.buildOptionLong(mockedGateway, DURATION_OPTION_NAME, 10, INTEGER_PARAMETER_TYPE));
        when(mockedEvent.reply()).thenReturn(mockedCallbackReply);
        when(mockedCallbackReply.withEphemeral(anyBoolean())).thenReturn(mockedCallbackReply);
        when(mockedEvent.getInteraction()).thenReturn(mockedInteraction);
        when(mockedInteraction.getUser()).thenReturn(new User(mockedGateway, mock(UserData.class)));
    }

    @Test
    void testGetNameIsCorrect() {
        assertEquals(CommandNameEnum.LIMIT_COMMAND.getName(), command.getName());
    }

    @Test
    void testHandleIsCorrect() {
        when(mockedCallbackReply.withEphemeral(anyBoolean())).thenReturn(mockedCallbackReply);
        when(mockedGameLimitController.limit(any(User.class), anyLong())).thenReturn(Boolean.TRUE);
        command.handle(mockedEvent);
        verify(mockedEvent, times(1)).reply();
        verify(mockedCallbackReply, times(1)).withEphemeral(Boolean.TRUE);
        verify(mockedCallbackReply, times(1)).withContent("Duration of 10 minutes noted!");
        verify(mockedGameLimitController, times(1)).limit(any(User.class), anyLong());
    }

    @Test
    void testHandleIsNotCorrect() {
        when(mockedGameLimitController.limit(any(User.class), anyLong())).thenReturn(Boolean.FALSE);
        command.handle(mockedEvent);
        verify(mockedEvent, times(1)).reply();
        verify(mockedCallbackReply, times(1)).withEphemeral(Boolean.TRUE);
        verify(mockedCallbackReply, times(1)).withContent("An error occured.");
        verify(mockedGameLimitController, times(1)).limit(any(User.class), anyLong());
    }

}