package DybekTutorial.com.restAPI.service;

import DybekTutorial.com.restAPI.model.Post;
import DybekTutorial.com.restAPI.model.User;
import DybekTutorial.com.restAPI.repository.PostRepository;
import DybekTutorial.com.restAPI.repository.UsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private static final int PAGE_SIZE = 2;

    public UserService(UsersRepository usersRepository, PostRepository postRepository) {
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
    }

    public List<User> getUsers(int page, Sort.Direction sort) {
        return usersRepository.findAllUsers(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    public User getSingleUser(int id) {
        return usersRepository.findById(id).orElseThrow();
    }

    public List<User> getUsersWithPosts(int pageNumber, Sort.Direction sort) {
        // pobranie wszystkich uzytkownikow
        List<User> allUsers = usersRepository.findAllUsers(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sort, "id")));
        // lista id uzytkownikow z pobranych wyzej uzytkownikow
        List<Integer> ids = allUsers.stream().map(User::getUid).collect(Collectors.toList());
        // pobranie wszystkich postow z listy id powyzej
        List<Post> posts = postRepository.findAllByIdIn(ids);


        allUsers.forEach(user -> user.setPostList(extractPosts(posts, user.getUid())));
        return allUsers;
    }

    private List<Post> extractPosts(List<Post> posts, int uid) {
        return posts.stream().filter(post -> post.getId() == uid).collect(Collectors.toList());
    }

    public User addUser(User user) {
        return usersRepository.save(user);
    }

    public User editUser(User user) {
        return usersRepository.save(user);
    }
}
