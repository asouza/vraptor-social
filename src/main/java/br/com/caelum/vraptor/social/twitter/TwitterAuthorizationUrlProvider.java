package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import org.scribe.oauth.OAuthService;

@Component
@ApplicationScoped
public class TwitterAuthorizationUrlProvider {

    private OAuthService service;

    public TwitterAuthorizationUrlProvider(TwitterOAuthService twitterOAuthService) {
        this.service = twitterOAuthService.get();
    }

    public String getUrl(){
        return service.getAuthorizationUrl(service.getRequestToken());
    }

}
