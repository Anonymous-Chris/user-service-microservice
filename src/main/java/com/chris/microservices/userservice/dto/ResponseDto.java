package com.chris.microservices.userservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private DepartmentDto department;
    private UserDto user;
}