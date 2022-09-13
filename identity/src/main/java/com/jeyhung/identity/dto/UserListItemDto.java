package com.jeyhung.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListItemDto {
    private long id;
    private String fullName;
    private String role;
    private String email;
}
