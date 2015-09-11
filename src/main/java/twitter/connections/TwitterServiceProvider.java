package twitter.connections;

import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

public final class TwitterServiceProvider extends AbstractOAuth1ServiceProvider<Twitter> {

    public TwitterServiceProvider(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret, 
        		"https://twitter.com/oauth/request_token",
                "https://twitter.com/oauth/authorize",
                "https://twitter.com/oauth/access_token"));
    }

    public Twitter getApi(String accessToken, String secret) {
        return new TwitterTemplate(getConsumerKey(), getConsumerSecret(), accessToken, secret);
    }

}