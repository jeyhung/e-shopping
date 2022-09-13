package com.jeyhung.identity.service;

import com.jeyhung.identity.dto.UserFilterDto;
import com.jeyhung.identity.dto.UserListItemDto;
import com.jeyhung.identity.dto.common.ResultDto;
import com.jeyhung.identity.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    ResultDto<UserListItemDto> getAll(UserFilterDto userFilterDto);
    User confirm(long id, User obj);
}
