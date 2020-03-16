package com.wallet.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.api.entity.User;
import com.wallet.api.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String EMAIL = "user@teste.com.br";
    private static final String NAME = "Teste";
    private static final String PASSWORD = "volume345";
    private static final String URL="/user";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    public void testSave() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    public User getMockUser() {
        User u = new User();

        u.setEmail(EMAIL);
        u.setName(NAME);
        u.setPassword(PASSWORD);

        return u;
    }

    public String getJsonPayLoad() throws JsonProcessingException {
        User dto = new UserDTO();
        dto.setEmail(EMAIL);
        dto.setName(NAME);
        dto.setPassword(PASSWORD);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }
}
