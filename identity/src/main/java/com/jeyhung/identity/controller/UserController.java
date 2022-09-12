package com.jeyhung.identity.controller;

import com.jeyhung.identity.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "users/")
public class UserController {

    @GetMapping(value = "/all")
    public List<UserDto> get() {
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto = UserDto.builder().id(1).firstName("Jeyhun").lastName("Gasimov").build();
        userDtos.add(userDto);

        return userDtos;
    }
}
