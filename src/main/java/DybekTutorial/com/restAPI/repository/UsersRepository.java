package DybekTutorial.com.restAPI.repository;

import DybekTutorial.com.restAPI.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    @Query("select u from users u ")
    List<User> findAllUsers(Pageable page);
}
