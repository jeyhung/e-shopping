package com.jeyhung.identity.controller;

import com.jeyhung.identity.dto.UserFilterDto;
import com.jeyhung.identity.dto.UserListItemDto;
import com.jeyhung.identity.dto.common.ResultDto;
import com.jeyhung.identity.model.UserRole;
import com.jeyhung.identity.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResultDto<UserListItemDto> getAll(@RequestHeader(required = false) String firstName,
                                             @RequestHeader(required = false) String lastName,
                                             @RequestHeader(required = false) String email,
                                             @RequestHeader(required = false) UserRole role,
                                             @RequestHeader(required = false, defaultValue = "0") int page,
                                             @RequestHeader(required = false, defaultValue = "10") int pageSize) {
        return userService.getAll(UserFilterDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .role(role)
                .page(page)
                .pageSize(pageSize)
                .build());
    }

    @GetMapping(value = "/byid")
    public String getById() {
        UserFilterDto userFilterDto = new UserFilterDto();
        userFilterDto.setPage(0);
        userFilterDto.setPageSize(10);
        userFilterDto.setFirstName("Jeyhun");

        userService.getAll(userFilterDto);
        return "test";
    }
}
