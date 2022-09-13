package com.example.chat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.chat.ControllerTestConfiguration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

@WebMvcTest(controllers = {ChatController.class})
@ContextConfiguration(classes = {ControllerTestConfiguration.class})
public class ChatControllerIntTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ChatController controller;

    @Test
    public void shouldPostMessage() throws Exception {
        var params = new LinkedMultiValueMap<String, String>();
        params.put("content", List.of("This is a message"));
        var result = mockMvc.perform(post("/chat/messages").params(params))
            .andExpect(status().isOk())
            .andReturn();

        var mav = result.getModelAndView();
        assertEquals("chat", mav.getViewName());
        assertNotNull(mav.getModel().get("messages"));
    }

    @Test
    public void shouldReturnMessages() throws Exception {
        var result = mockMvc.perform(get("/chat/messages"))
            .andExpect(status().isOk())
            .andReturn();

        var mav = result.getModelAndView();

        assertEquals("chat", mav.getViewName());
        assertNotNull(mav.getModel().get("messages"));
    }
}
