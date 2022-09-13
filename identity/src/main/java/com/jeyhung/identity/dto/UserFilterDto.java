package com.jeyhung.identity.dto;

import com.jeyhung.identity.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterDto {
    private String firstName;
    private String lastName;
    private UserRole role;
    private String email;
    private int page;
    private int pageSize;
}
