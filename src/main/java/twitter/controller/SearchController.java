package twitter.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter.model.TwitterTemplateGenerator;

@Controller
public class SearchController {

	private Twitter twitter;
	@Autowired
	private TwitterTemplateGenerator twitterTemplateGenerator;

	@Inject
	public SearchController(Twitter twitter) {
		this.twitter = twitter;
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showTrends(@RequestParam("query") String query, Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();
		System.out.println(query);
		model.addAttribute("timeline", twitter.searchOperations().search(query).getTweets());
		return "timeline";
	}
}
