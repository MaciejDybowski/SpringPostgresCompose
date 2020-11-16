package DybekTutorial.com.restAPI.controller;

import DybekTutorial.com.restAPI.controller.dto.UserDto;
import DybekTutorial.com.restAPI.model.Post;
import DybekTutorial.com.restAPI.model.User;
import DybekTutorial.com.restAPI.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {
    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return UserDtoMapper.mapToUsersDtos(userService.getUsers(pageNumber, sortDirection));
    }

    @GetMapping("/users/posts")
    public List<User> getUsersWithPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {

        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return userService.getUsersWithPosts(pageNumber, sortDirection);
    }


    @GetMapping("/users/{id}")
    public User getSingleUser(@PathVariable int id) {
        return userService.getSingleUser(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }
}
