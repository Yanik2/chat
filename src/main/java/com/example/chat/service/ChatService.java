package com.example.chat.service;

import com.example.chat.dto.MessageDto;
import com.example.chat.model.Message;

import java.util.List;

public interface ChatService {
    List<Message> addMessage(MessageDto messageDto);
    List<Message> getMessages();
}
