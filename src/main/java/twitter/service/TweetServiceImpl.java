package twitter.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import twitter.model.CurrentUser;
import twitter.model.Tweet;
import twitter.model.User;
import twitter.repository.TweetRepository;

@Service
public class TweetServiceImpl implements TweetService {
	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private UserService userService;

	@Override
	public Tweet create(Tweet tweet) {
		tweet.setUser((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		tweet.setCreatedAt(Calendar.getInstance().getTime());
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> getCurrentUserTweets() {
		return tweetRepository
				.findByUserOrderByCreatedAtDesc((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@Override
	public List<Tweet> getFollowersTweets() {
		List<Tweet> followingTweets = new ArrayList<>();
		for(User following:userService.getFollowing()) {
			followingTweets.addAll(getUserTweets(following));
		}
		return followingTweets;
	}

	@Override
	public List<Tweet> getAllTweets() {
		List<Tweet> allTweets = new ArrayList<>();
		allTweets.addAll(getCurrentUserTweets());
		allTweets.addAll(getFollowersTweets());
		allTweets.sort(new Comparator<Tweet>() {

			@Override
			public int compare(Tweet o1, Tweet o2) {
				return o2.getCreatedAt().compareTo(o1.getCreatedAt());
			}
		});
		return allTweets;
	}

	@Override
	public List<Tweet> getUserTweets(String username) {
		return tweetRepository.findByUserOrderByCreatedAtDesc(userService.findUserByUsername(username));
	}
	
	@Override
	public List<Tweet> getUserTweets(User user) {
		return tweetRepository.findByUserOrderByCreatedAtDesc(user);
	}

}