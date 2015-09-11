package twitter.controller;
import java.security.Principal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter.model.TwitterTemplateGenerator;

@Controller
public class ProfileController {
	@Autowired
	private TwitterTemplateGenerator twitterTemplateGenerator;
	
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		Twitter twitter = twitterTemplateGenerator.getTwitterTemplate();
		model.addAttribute("profile", twitter.userOperations().getUserProfile());
		return "profile";
	}
	
}