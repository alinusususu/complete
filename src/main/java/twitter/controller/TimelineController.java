package twitter.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter.model.TwitterTemplateGenerator;

@Controller
public class TimelineController {

	@Autowired
	private TwitterTemplateGenerator twitterTemplateGenerator;

	private Twitter twitter;

	private ConnectionRepository connectionRepository;

	@Inject
	public TimelineController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping("/")
	public String home(Model model) {
		/*
		 * if (connectionRepository.findPrimaryConnection(Twitter.class) ==
		 * null) { return "redirect:/connect/twitter"; }
		 */
		twitter = twitterTemplateGenerator.getTwitterTemplate();
		model.addAttribute("timeline", twitter.timelineOperations().getHomeTimeline());
		return "timeline";
	}

	@RequestMapping("/myTweets")
	public String myTweets(Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();
		model.addAttribute("timeline", twitter.timelineOperations().getUserTimeline());
		return "timeline";
	}
	
	@RequestMapping(value="/timeline", method=RequestMethod.GET)
	public String timelineFor(@RequestParam("screenName") String screenName,Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();
		model.addAttribute("timeline", twitter.timelineOperations().getUserTimeline(screenName));
		return "timeline";
	}
	@RequestMapping(value="/tweet", method=RequestMethod.POST)
	public String postTweet(String message) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();
		twitter.timelineOperations().updateStatus(message);
		return "redirect:/";
	}

}
