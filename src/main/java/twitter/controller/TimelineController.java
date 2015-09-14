package twitter.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter.model.CurrentUser;
import twitter.model.Tweet;
import twitter.service.TweetService;
import twitter.service.UserService;


@Controller
public class TimelineController {
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:/timeline";
	}

	@RequestMapping(value="/tweet", method=RequestMethod.POST)
	public String postTweet(String text) {
		Tweet tweet = new Tweet();
		tweet.setText(text);
		tweetService.create(tweet);
		return "redirect:/timeline";
	}
	
	
	
	@RequestMapping(value="/myTweets", method=RequestMethod.GET)
	public String timelineFor(Model model) {
		model.addAttribute("timeline", tweetService.getCurrentUserTweets());
		return "timeline";
	}
	
	@RequestMapping(value="/for", method=RequestMethod.GET)
	public String timelineFor(@RequestParam("username") String username,Model model) {
		model.addAttribute("followed", userService.isFollowing(username));
		model.addAttribute("user", userService.findUserByUsername(username));
		model.addAttribute("tweets", tweetService.getUserTweets(username));
		return "profile";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model model) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", currentUser);
		return "test";
	}
	@RequestMapping(value="/timeline", method=RequestMethod.GET)
	public String timeline(Model model) {
		model.addAttribute("timeline", tweetService.getAllTweets());
		return "timeline";
	}
	

}
