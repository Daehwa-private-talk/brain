package com.daehwa.brain.app.mapper;

import com.daehwa.brain.app.jpa.UserEntity;
import com.daehwa.brain.app.vo.RequestUser;
import com.daehwa.brain.app.model.UserDto;
import com.daehwa.brain.app.vo.ResponseUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto requestToDto(RequestUser request);
    UserEntity dtoToEntity(UserDto userDto);
    ResponseUser dtoToResponse(UserDto userDto);
}
