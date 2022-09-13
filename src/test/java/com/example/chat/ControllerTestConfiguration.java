package com.example.chat;

import com.example.chat.dao.ChatRepository;
import com.example.chat.service.ChatService;
import com.example.chat.service.impl.ChatServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ControllerTestConfiguration {
    @MockBean
    private ChatRepository chatRepositoryMock;

    @Bean
    ChatService chatService() {
        return new ChatServiceImpl(chatRepositoryMock);
    }
}