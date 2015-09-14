package twitter.service;

import java.util.List;

import twitter.model.User;
import twitter.model.UserCreateForm;

public interface UserService {

    User create(UserCreateForm form);
    
    void follow(String username);
    
	void unfollow(String followed);

	User findUserByEmail(String email);
	
	User findUserByUsername(String username);

	List<User> getFollowing();

	User getCurrentUser();

	List<User> getFollowers();

	boolean isFollowing(String username);


}