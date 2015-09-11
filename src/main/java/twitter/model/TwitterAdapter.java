package twitter.model;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;

public class TwitterAdapter implements ApiAdapter<Twitter> {

    public boolean test(Twitter twitter) {
        try {
            twitter.userOperations().getUserProfile();
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    public void setConnectionValues(Twitter twitter, ConnectionValues values) {
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        values.setProviderUserId(Long.toString(profile.getId()));
        values.setDisplayName("@" + profile.getScreenName());
        values.setProfileUrl(profile.getProfileUrl());
        values.setImageUrl(profile.getProfileImageUrl());
    }
    
    public UserProfile fetchUserProfile(Twitter twitter) {
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        return new UserProfileBuilder().setName(profile.getName()).setUsername(
            profile.getScreenName()).build();
    }
    
    public void updateStatus(Twitter twitter, String message) {
        twitter.timelineOperations().updateStatus(message);	
    }
	
}

