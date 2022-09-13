package com.example.chat.controller;

import com.example.chat.model.Message;
import com.example.chat.service.ChatService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private ChatService chatService;

    public LoginController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String login(HttpServletRequest httpServletRequest,
                        @RequestParam("user_name") String userName,
                        Model model) {
        httpServletRequest.getSession().setAttribute("user", userName);
        List<Message> messages = chatService.getMessages();
        model.addAttribute("messages", messages);
        return "chat";
    }
}
