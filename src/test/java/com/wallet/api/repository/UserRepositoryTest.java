package com.wallet.api.repository;

import com.wallet.api.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    private static final String EMAIL = "stefano@gmail.com";
    @Autowired
    UserRepository repository;

    @Before
    public void setup(){
        User u = new User();
        u.setName("Stefano");
        u.setEmail(EMAIL);
        u.setPassword("123456");

        repository.save(u);
    }
    @After
    public void tearDown(){
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        User u = new User();
        u.setName("Test");
        u.setPassword("volume345");
        u.setEmail("user@gmail.com");

        User response = repository.save(u);

        assertNotNull(response);
    }

    public void testFindByEmail() {
        Optional<User> response = repository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}
