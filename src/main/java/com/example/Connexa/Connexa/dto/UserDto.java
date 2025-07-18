package com.example.Connexa.Connexa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class UserDto {
    private Long Id;
    private String username;
    private String email;
    private String mobile;
    private String bio;
    private String gender;
    private String image;
    private String password;
}
