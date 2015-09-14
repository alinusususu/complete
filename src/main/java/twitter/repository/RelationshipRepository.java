package twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitter.model.Relationship;
import twitter.model.User;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    List<Relationship> findByFollower(User user);
    List<Relationship> findByFollowed(User user);
    Relationship findByFollowerAndFollowed(User follower,User followed);
}