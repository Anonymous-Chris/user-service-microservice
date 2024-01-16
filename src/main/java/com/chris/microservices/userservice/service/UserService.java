package com.chris.microservices.userservice.service;


import com.chris.microservices.userservice.dto.ResponseDto;
import com.chris.microservices.userservice.model.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}
