package com.example.chat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.chat.ControllerTestConfiguration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

@WebMvcTest(controllers = {LoginController.class})
@ContextConfiguration(classes = {ControllerTestConfiguration.class})
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LoginController loginController;

    @Test
    public void shouldSaveUser() throws Exception {
        var params = new LinkedMultiValueMap<String, String>();
        params.put("user_name", List.of("Tom"));

        var result = mockMvc.perform(post("/login").params(params))
            .andExpect(status().isOk())
            .andReturn();

        var user = result.getRequest().getSession().getAttribute("user");
        var mav = result.getModelAndView();

        assertEquals("Tom", (String)user);
        assertEquals("chat", mav.getViewName());
        assertNotNull(mav.getModel().get("messages"));
    }
}
