package twitter.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import twitter.model.Relationship;
import twitter.model.User;
import twitter.model.UserCreateForm;
import twitter.repository.RelationshipRepository;
import twitter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setUsername(form.getUsername());
        return userRepository.save(user);
    }

	@Override
	public void follow(String followed) {
		Relationship relationship = new Relationship();
		relationship.setFollower( getCurrentUser());
		relationship.setFollowed(findUserByUsername(followed));
		relationshipRepository.save(relationship);
	}
	
	@Override
	public void unfollow(String followed) {
		Relationship relationship = relationshipRepository.findByFollowerAndFollowed(getCurrentUser(), findUserByUsername(followed));
		
		relationshipRepository.delete(relationship);
	}

	@Override
	public List<User> getFollowing() {
		List<User> users = new ArrayList<>();
		List<Relationship>relationships =relationshipRepository.findByFollower(getCurrentUser());
		for(Relationship relationship:relationships) {
			users.add(relationship.getFollowed());
		}
		return users;
	}
	
	@Override
	public boolean isFollowing(String username) {
		Relationship relationship = relationshipRepository.findByFollowerAndFollowed(getCurrentUser(), findUserByUsername(username));
		if(relationship!= null) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<User> getFollowers() {
		List<User> users = new ArrayList<>();
		List<Relationship>relationships =relationshipRepository.findByFollowed(getCurrentUser());
		for(Relationship relationship:relationships) {
			users.add(relationship.getFollower());
		}
		return users;
	}
	
	@Override
	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public User findUserByUsername(String username) {
		
		return userRepository.findUserByUsername(username);
	}


}