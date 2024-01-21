package com.laborotoryproject.weatherparser.service;

import com.laborotoryproject.weather.parser.dao.MyUserDao;
import com.laborotoryproject.weather.parser.entity.MyUser;
import com.laborotoryproject.weather.parser.entity.enums.Role;
import com.laborotoryproject.weather.parser.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private MyUserDao myUserDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername_userFound() {
        String username = "testUser";
        String password = "testPassword";

        Role role = Role.USER;

        MyUser myUser = MyUser
                .builder()
                .login(username)
                .password(password)
                .role(role)
                .build();

        when(myUserDao.findByLogin(username)).thenReturn(myUser);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", passwordEncoder.encode("testPassword"));
        assertTrue(role.getGrantedAuthorities().containsAll(userDetails.getAuthorities()));

        verify(myUserDao, times(1)).findByLogin(username);
        verify(passwordEncoder, times(1)).encode(password);
    }

    @Test
    void testLoadUserByUsername_userNotFound() {
        String username = "nonexistentUser";

        when(myUserDao.findByLogin(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}