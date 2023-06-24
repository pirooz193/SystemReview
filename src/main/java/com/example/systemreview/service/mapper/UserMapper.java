package com.example.systemreview.service.mapper;

import com.example.systemreview.domain.User;
import com.example.systemreview.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}
