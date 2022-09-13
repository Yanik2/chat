package com.example.chat.service.impl;

import com.example.chat.dao.ChatRepository;
import com.example.chat.dto.MessageDto;
import com.example.chat.model.Message;
import com.example.chat.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public List<Message> addMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setDateTime(messageDto.getDateTime());
        message.setUserName(messageDto.getUserName());
        chatRepository.save(message);
        return chatRepository.findLast50();
    }

    @Override
    public List<Message> getMessages() {
        return chatRepository.findLast50();
    }
}
