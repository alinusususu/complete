package twitter.service;

import java.util.List;

import twitter.model.Tweet;
import twitter.model.User;

public interface TweetService {

    Tweet create(Tweet tweet);
	List<Tweet> getCurrentUserTweets();
	List<Tweet> getFollowersTweets();
	List<Tweet> getAllTweets();
	List<Tweet> getUserTweets(String username);
	List<Tweet> getUserTweets(User user);

}