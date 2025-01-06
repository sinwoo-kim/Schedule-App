package org.example.todo.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.exception.invalid.InvalidRequestException;
import org.example.todo.user.dto.request.UpdateUserRequestDto;
import org.example.todo.user.dto.response.UpdateUserResponseDto;
import org.example.todo.user.dto.response.UserResponseDto;
import org.example.todo.user.entity.User;
import org.example.todo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    UserRepository userRepository;

    // 1. READ :: ALL
    public List<UserResponseDto> getUserList() {
        List<User> foundUserList = userRepository.findAll();
        return foundUserList.stream()
                            .map(UserResponseDto::toDto)
                            .collect(Collectors.toList());
    }

    // 2. READ :: FIND USER BY ID
    public UserResponseDto getUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        return UserResponseDto.toDto(foundUser);
    }

    // 3. UPDATE
    @Transactional
    public UpdateUserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        foundUser.update(updateUserRequestDto.username());
        return UpdateUserResponseDto.toDto(foundUser);
    }

    // 4. DELETE
    @Transactional
    public void deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        userRepository.deleteById(userId);
    }
}
