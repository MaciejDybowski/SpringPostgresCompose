package DybekTutorial.com.restAPI.controller;

import DybekTutorial.com.restAPI.controller.dto.UserDto;
import DybekTutorial.com.restAPI.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {

    private UserDtoMapper() {

    }

    public static List<UserDto> mapToUsersDtos(List<User> users) {
        return users.stream()
                .map(user -> mapToUserDtos(user))
                .collect(Collectors.toList());
    }

    public static UserDto mapToUserDtos(User user) {
        return UserDto.builder()
                .uid(user.getUid())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
