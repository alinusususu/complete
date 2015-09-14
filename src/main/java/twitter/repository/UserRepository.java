package twitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitter.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

	User findUserByUsername(String username);
}