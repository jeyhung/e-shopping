package com.jeyhung.identity.service.impl;

import com.jeyhung.identity.dto.UserFilterDto;
import com.jeyhung.identity.dto.UserListItemDto;
import com.jeyhung.identity.dto.common.ResultDto;
import com.jeyhung.identity.model.User;
import com.jeyhung.identity.model.specification.UserSpecification;
import com.jeyhung.identity.repository.UserRepository;
import com.jeyhung.identity.service.UserService;
import com.jeyhung.identity.shared.SearchCriteria;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(User.class, UserListItemDto.class)
                .addMappings(mapper -> {
                    Converter<User, String> fullName = ctx -> ctx.getSource().getFirstName() + " " + ctx.getSource().getLastName();
                    mapper.using(fullName).map(src -> src, UserListItemDto::setFullName);
                });
    }

    @Override
    public ResultDto<UserListItemDto> getAll(UserFilterDto userFilterDto) {
        if(userFilterDto == null) {
            return null;
        }

        Specification<User> specification = Specification.where(null);

        if(userFilterDto.getFirstName() != null) {
            UserSpecification specFirstName = new UserSpecification(new SearchCriteria("firstName", ":", userFilterDto.getFirstName()));
            specification = specification.and(specFirstName);
        }

        if(userFilterDto.getLastName() != null) {
            UserSpecification specLastName = new UserSpecification(new SearchCriteria("lastName", ":", userFilterDto.getLastName()));
            specification = specification.and(specLastName);
        }

        if(userFilterDto.getRole() != null) {
            UserSpecification specRole = new UserSpecification(new SearchCriteria("role", ":", userFilterDto.getRole()));
            specification = specification.and(specRole);
        }

        if(userFilterDto.getEmail() != null) {
            UserSpecification specEmail = new UserSpecification(new SearchCriteria("email", ":", userFilterDto.getEmail()));
            specification = specification.and(specEmail);
        }

        Pageable pageable = PageRequest.of(userFilterDto.getPage(), userFilterDto.getPageSize());

        List<User> users = userRepository.findAll(specification);
        long count = userRepository.count(specification);

        return new ResultDto<>(users.stream()
                .map(user -> modelMapper.map(user, UserListItemDto.class))
                .collect(Collectors.toList()), count);
    }

    @Override
    public User confirm(long id, User obj) {
        return null;
    }
}
