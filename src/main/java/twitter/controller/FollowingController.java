package twitter.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter.model.TwitterTemplateGenerator;
@Controller
public class FollowingController {
	private Twitter twitter;
	
	@Autowired
	private TwitterTemplateGenerator twitterTemplateGenerator;
	private ConnectionRepository connectionRepository;

	@Inject
	public FollowingController(Twitter twitter, ConnectionRepository connectionRepository) {
	     this.twitter = twitter;
	     this.connectionRepository = connectionRepository;
	}

	@RequestMapping(value = "/following", method = RequestMethod.GET)
	public String following(Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();  //asta va disparea cand reusesc sa fac conecxiunea.
		model.addAttribute("profiles", twitter.friendOperations().getFriends());
		return "following";
	}

	@RequestMapping(value = "/followers", method = RequestMethod.GET)
	public String followers(Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();  //asta va disparea cand reusesc sa fac conecxiunea.
		model.addAttribute("profiles", twitter.friendOperations().getFollowers());
		return "following";
	}
}
