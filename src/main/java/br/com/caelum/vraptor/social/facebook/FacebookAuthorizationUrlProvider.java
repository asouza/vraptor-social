package br.com.caelum.vraptor.social.facebook;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import org.scribe.oauth.OAuthService;

@Component
@ApplicationScoped
public class FacebookAuthorizationUrlProvider {

    private FacebookOAuthService facebookOAuthService;

    public FacebookAuthorizationUrlProvider(FacebookOAuthService facebookOAuthService) {
        this.facebookOAuthService = facebookOAuthService;
    }

    public String getUrl(){
        return facebookOAuthService.getAuthorizationUrl();
    }

}
