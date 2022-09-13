package com.example.chat.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;

import com.example.chat.dao.ChatRepository;
import com.example.chat.dto.MessageDto;
import com.example.chat.model.Message;
import com.example.chat.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChatServiceTest {
    private ChatService chatService;
    private ChatRepository chatRepositoryMock;

    @BeforeEach
    public void setUp() {
        chatRepositoryMock = Mockito.mock(ChatRepository.class);
        chatService = new ChatServiceImpl(chatRepositoryMock);
    }

    @Test
    public void shouldCallChatRepository() {
        MessageDto messageDto = new MessageDto();

        chatService.addMessage(messageDto);

        verify(chatRepositoryMock).save(isA(Message.class));
    }

    @Test
    public void shouldCallGetMessages() {
        chatService.getMessages();
        verify(chatRepositoryMock).findLast50();
    }
}
