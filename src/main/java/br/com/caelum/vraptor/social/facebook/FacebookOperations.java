package br.com.caelum.vraptor.social.facebook;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.SocialIntegrationReader;
import br.com.caelum.vraptor.social.twitter.TwitterProfile;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class FacebookOperations {

    private FacebookOAuthService facebookOAuthService;
    private String code;
    private OAuthService oauthService;
    private Verifier verifier;
    private Token accessToken;
    private FacebookProfile facebookProfile;

    public FacebookOperations(HttpServletRequest request, FacebookOAuthService facebookOAuthService) {
        this.code = request.getParameter("code");
        if(code ==null){
            throw new IllegalStateException("You should not try to use FacebookOperations out of callback context");
        }
        this.facebookOAuthService = facebookOAuthService;
        this.oauthService = facebookOAuthService.getOAuthService();

    }

    @PostConstruct
    public void build() {
        verifier = new Verifier(code);
        accessToken = facebookOAuthService.getAccessToken(verifier);
        initProfile();
    }

    private void initProfile() {
        facebookProfile = facebookOAuthService.signRequest("https://graph.facebook.com/me",accessToken,FacebookProfile.class);
    }

    public FacebookProfile getProfile(){
        return facebookProfile;
    }
}
