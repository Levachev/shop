package com.example.shop.repo;

import com.example.shop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo underTest;

    @Test
    void shouldCheckedIfUserFindsByEmail() {
        //given
        User user = User.builder()
                .email("1@mail.ru")
                .firstname("roman")
                .lastname("romanov")
                .password("123")
                .role("USER_ROLE")
                .build();

        underTest.save(user);

        //when
        Optional<User> result = underTest.findByEmail("1@mail.ru");

        //then
        assertTrue(result.isPresent());
        User userResult = result.get();
        assertEquals(userResult.getEmail(), "1@mail.ru");

    }
}