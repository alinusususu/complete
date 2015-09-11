package twitter.model;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class TwitterTemplateGenerator {
	//datele de conectare ... in mod normal este nevoie doar de primele doua 
	//si dupa prin request face rost si de urmatoarele doua... dar asta iti spuneam ca nu am reusit inca
	/*private TwitterTemplate twitterTemplate= new TwitterTemplate(
			"73PSzgYeI2rdVlzqU2pfLXS1x", 
			"ClcGYGeTN3fgpLBFOVKWFi3ottEzzkQTudwiIuO13O5wfpWdZD", 
			"3591906443-XjEyyn3H14nnRSZljHhocdN9tvUlDh9qPahBZzF", 
			"kt6CfiPyEYwAj2w63HqkUVZfVoK6urRqp5SRMyj65l5pw");*/
	private TwitterTemplate twitterTemplate= new TwitterTemplate(
			"57iEPDecZAjeY6I6sb9JgUMxg", 
			"PHlPpHTonHyNDEhASnkbD5DQp4IagTveMUEyoDBf2gGAHRh7ZG", 
			"3591906443-C3m1AmMTiPwH45feMl0ZvTfHysF84VQvQ82JRmz", 
			"MUG74VnW0AI8JbM3nC4mMnJPH1AqZT02seqsRenlTFvgr");
	
	public TwitterTemplate getTwitterTemplate() {
		return twitterTemplate;
	}
   
}