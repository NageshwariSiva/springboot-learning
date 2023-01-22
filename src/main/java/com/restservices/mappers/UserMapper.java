package com.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.restservices.dto.UserMapStructDto;
import com.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User To UserMsDto
	@Mappings({
	@Mapping(source= "email", target="emailaddress"),
	@Mapping(source = "role", target="rolename"),
	@Mapping(source = "id", target="userid")
	})
	UserMapStructDto userToUserMsDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMapStructDto> usersToUserDtos(List<User> users);

}