package DybekTutorial.com.restAPI.controller.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@Builder
public class UserDto {

    private int uid;
    private String name;
    private String surname;
}
