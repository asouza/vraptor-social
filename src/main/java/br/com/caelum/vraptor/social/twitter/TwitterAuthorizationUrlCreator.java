package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.Component;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

@Component
public class TwitterAuthorizationUrlCreator {

    private OAuthService service;

//    public TwitterAuthorizationUrlCreator(TwitterApi twitterApi) {
//        this.twitterApi = twitterApi;
//    }
//
//    public String getAuthorizationUrl() {
//        OAuthService service = new ServiceBuilder().provider(TwitterApi.class).
//                apiKey(environment.get("twitter.consumer_key")).
//                apiSecret(environment.get("twitter.consumer_secret_key")).build();
//        Token requestToken = service.getRequestToken();
//        return service.getAuthorizationUrl(requestToken);
//    }
}
