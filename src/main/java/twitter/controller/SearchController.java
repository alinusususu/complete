package twitter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter.service.TweetService;
import twitter.service.UserService;


@Controller
public class SearchController {

	@Autowired
	private TweetService tweetService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("query") String query, Model model) {
		model.addAttribute("followed", userService.isFollowing(query));
		model.addAttribute("user", userService.findUserByUsername(query));
		model.addAttribute("tweets", tweetService.getUserTweets(query));
		return "profile";
	}
}
