package com.example.chat.controller;

import com.example.chat.dto.MessageDto;
import com.example.chat.model.Message;
import com.example.chat.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/messages")
    public String addMessage(@RequestParam String content,
                             HttpServletRequest request,
                             Model model) {
        MessageDto messageDto = new MessageDto();
        String username = (String)request.getSession().getAttribute("user");
        messageDto.setUserName(username);
        messageDto.setContent(content);
        messageDto.setDateTime(LocalDateTime.now());

        List<Message> messages = chatService.addMessage(messageDto);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        List<Message> messages = chatService.getMessages();
        model.addAttribute("messages", messages);
        return "chat";
    }
}
