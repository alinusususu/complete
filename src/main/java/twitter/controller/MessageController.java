package twitter.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter.model.MessageForm;
import twitter.model.TwitterTemplateGenerator;
@Controller
public class MessageController {
	@Autowired
	private TwitterTemplateGenerator twitterTemplateGenerator;
	
    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public MessageController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping(value="/messages", method=RequestMethod.GET)
	public String inbox(Model model) {
    	twitter = twitterTemplateGenerator.getTwitterTemplate();  //asta va disparea cand reusesc sa fac conecxiunea.
		model.addAttribute("directMessages", twitter.directMessageOperations().getDirectMessagesReceived());
		model.addAttribute("dmListType", "Received");
		model.addAttribute("messageForm", new MessageForm());
		return "messages";
	}

	@RequestMapping(value="/messages/sent", method=RequestMethod.GET)
	public String sent(Model model) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();  //asta va disparea cand reusesc sa fac conecxiunea.
		model.addAttribute("directMessages", twitter.directMessageOperations().getDirectMessagesSent());
		model.addAttribute("dmListType", "Sent");
		model.addAttribute("messageForm", new MessageForm());
		return "messages";
	}

	@RequestMapping(value="/messages", method=RequestMethod.POST)
	public String sent(MessageForm message) {
		twitter = twitterTemplateGenerator.getTwitterTemplate();  //asta va disparea cand reusesc sa fac conecxiunea.
		twitter.directMessageOperations().sendDirectMessage(message.getTo(), message.getText());
		return "redirect:/messages";
	}
}
