package com.harsh.RentRead.user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.controller.exchanges.AddRequestRoleDto;
import com.harsh.RentRead.user.controller.exchanges.UserUpdateDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.entity.enums.Role;
import com.harsh.RentRead.user.repository.UserRepository;
import com.harsh.RentRead.user.services.implementation.UserServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    
    @InjectMocks
    private UserServiceImplementation userService;

    @MockBean
    private UserRepository userRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImplementation(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void getUser() {
        log.info("Starting 'getUser' test case...");
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result = userService.getAllUsers();
        log.info("Number of users returned by getAllUsers(): {}", result.size());

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    @Description("IllegalArgumentException when userId is null")
    public void getUserByUserIdIllegalArgumentExceptionTest() {
        log.info("Starting getUserByUserIdIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByUserId(null));
        log.info("getUserByUserIdIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void getUserByUserIdTestResourceNotFoundExceptionTest() {
        log.info("Starting getUserByUserIdTestResourceNotFoundExceptionTest");
        when(userRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByUserId(1L));
        log.info("getUserByUserIdTestResourceNotFoundExceptionException completed successfully");
    }

    @Test
    public void getUserByUserIdTest() {
        log.info("Starting getUserByUserIdTest");
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserByUserId(1L);

        // Assert
        assertEquals(user.getId(), userDto.getId());
        log.info("getUserByUserIdTest completed successfully");
    }

    @Test
    @Description("IllegalArgumentException when email is null")
    public void getUserByEmailIllegalArgumentExceptionTest() {
        log.info("Starting getUserByEmailIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByEmail(null));
        log.info("getUserByEmailIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void getUserByEmailTestResourceNotFoundExceptionTest() {
        log.info("Starting getUserByEmailTestResourceNotFoundExceptionTest");
        when(userRepository.findByEmail(anyString())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByEmail("harsh@gmail.com"));
        log.info("getUserByEmailTestResourceNotFoundExceptionTest completed successfully");
    }

    @Test
    public void getUserByEmailTest() {
        log.info("Starting getUserByEmailTest");
        User user = new User();
        user.setEmail("mail.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserByUserId(1L);

        // Assert
        assertEquals(user.getEmail(), userDto.getEmail());
        log.info("getUserByEmailTest completed successfully");
    }

    @Test
    @Description("IllegalArgumentException when userId is null")
    public void deleteUserIllegalArgumentExceptionTest() {
        log.info("Starting deleteUserIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
        log.info("deleteUserIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void deleteUserTestResourceNotFoundExceptionTest() {
        log.info("Starting deleteUserTestResourceNotFoundExceptionTest");
        when(userRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1L));
        log.info("deleteUserTestResourceNotFoundExceptionTest completed successfully");
    }


    @Test
    @Description("IllegalArgumentException when userId is null")
    public void updateUserIllegalArgumentExceptionTest() {
        log.info("Starting updateUser");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(null, null));
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(1L, null));
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(null, new UserUpdateDto()));
        log.info("updateUser completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException user not found")
    public void updateUserTestResourceNotFoundExceptionTest() {
        log.info("Starting updateUserTestResourceNotFoundExceptionTest");
        when(userRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, new UserUpdateDto()));
        log.info("updateUserTestResourceNotFoundExceptionTest completed successfully");
    }

    @Test
    public void updateUserTest() {
        log.info("Starting updateUserTest");

        User user = new User();
        user.setId(1L);
        user.setFirstName("Harsh");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setFirstName("Sahil");
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setFirstName("Sahil");

        UserDto userDto = userService.updateUser(1L, userUpdateDto);

        // Assert
        assertEquals(userUpdateDto.getFirstName(), userDto.getFirstName());

        log.info("updateUserTest completed successfully");
    }

    @Test
    public void addRoleTest() {
        log.info("Starting addRoleTest");
        Long userId = 1L;
        Role role = Role.ADMIN;
        AddRequestRoleDto addRequestRoleDto = new AddRequestRoleDto(role);

        User user = new User();
        user.setId(userId);
        user.setEmail("harsh@example.com");
        user.addRole(Role.USER); // already has USER role

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setEmail("harsh@example.com");
        updatedUser.addRole(role);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        
        // add ADMIN role
        UserDto result = userService.addRole(userId, addRequestRoleDto);

        // Assert
        assertEquals(updatedUser.getId(), result.getId());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getRoles(), result.getRoles());

        log.info("addRoleTest completed successfully");
    }

    @Test
    public void addRoleUserNotFoundThrowsResourceNotFoundException() {
        log.info("Starting addRoleUserNotFoundThrowsResourceNotFoundException");

        when(userRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.addRole(1L, new AddRequestRoleDto()));

        log.info("addRoleUserNotFoundThrowsResourceNotFoundException completed successfully");
    }
}
