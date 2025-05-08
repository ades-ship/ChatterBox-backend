package com.substring.chat.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String userName;
    private String profile;
    private Long phoneNumber;
}
