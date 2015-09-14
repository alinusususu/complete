package twitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter.model.UserCreateForm;
import twitter.service.TweetService;
import twitter.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private TweetService tweetService;

	@RequestMapping(value = "/following", method = RequestMethod.GET)
	public String following(Model model) {
		model.addAttribute("users", userService.getFollowing());
		return "following";
	}

	@RequestMapping(value = "/followers", method = RequestMethod.GET)
	public String followers(Model model) {
		model.addAttribute("users", userService.getFollowers());
		return "following";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getUserCreatePage(Model model) {
		model.addAttribute("form", new UserCreateForm());
		return "user_create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form,
			BindingResult bindingResult) {
		System.out.println(form);
		if (bindingResult.hasErrors()) {
			return "user_create";
		}
		try {
			userService.create(form);
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("email.exists", "Email already exists");
			return "user_create";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String follow(@RequestParam("username") String username, Model model) {
		userService.follow(username);
		model.addAttribute("followed", userService.isFollowing(username));
		model.addAttribute("user", userService.findUserByUsername(username));
		model.addAttribute("tweets", tweetService.getUserTweets(username));
		return "profile";
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public String unfollow(@RequestParam("username") String username, Model model) {
		userService.unfollow(username);
		model.addAttribute("followed", userService.isFollowing(username));
		model.addAttribute("user", userService.findUserByUsername(username));
		model.addAttribute("tweets", tweetService.getUserTweets(username));
		return "profile";
	}

}
