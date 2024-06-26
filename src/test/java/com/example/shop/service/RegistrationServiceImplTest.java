package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.models.ExistEnum;
import com.example.shop.repo.CartRepo;
import com.example.shop.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private RegistrationServiceImpl underTest;
    @Mock
    private CartRepo cartRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldRegisterUser() {
        //given
        User user = User.builder()
                .email("email")
                .id(0L)
                .build();

        //when
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());

        ExistEnum result = underTest.registerUser(user);
        //then
        assertEquals(result, ExistEnum.NOT_EXIST);
        verify(cartRepo).save(any(Cart.class));
        verify(userRepo).save(any(User.class));
    }

    @Test
    void shouldNotRegisterUser() {
        //given
        User user = User.builder()
                .email("email")
                .id(0L)
                .build();

        //when
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));

        ExistEnum result = underTest.registerUser(user);
        //then
        assertEquals(result, ExistEnum.EXIST);
    }
}