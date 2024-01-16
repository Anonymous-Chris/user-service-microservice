package com.chris.microservices.userservice.service;

import com.chris.microservices.userservice.dto.DepartmentDto;
import com.chris.microservices.userservice.dto.ResponseDto;
import com.chris.microservices.userservice.dto.UserDto;
import com.chris.microservices.userservice.model.User;
import com.chris.microservices.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        User user = userRepository.findById(userId).get();
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8000/api/departments/" + user.getDepartmentId(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();
        System.out.println(responseEntity.getStatusCode());
        ResponseDto responseDto = ResponseDto.builder()
                .user(userDto)
                .department(departmentDto)
                .build();

        return responseDto;
    }
}
