package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.SocialIntegrationReader;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class TwitterOperations {

    private TwitterOAuthService twitterOAuthService;
    private String oauthToken;
    private String oauthVerifier;
    private OAuthService oauthService;
    private Verifier verifier;
    private Token accessToken;
    private TwitterProfile twitterProfile;

    public TwitterOperations(HttpServletRequest request, TwitterOAuthService twitterOAuthService){
        this.oauthToken = request.getParameter("oauth_token");
        this.oauthVerifier = request.getParameter("oauth_verifier");
        if (oauthToken == null || oauthVerifier == null) {
            throw new IllegalStateException("You should not try to use Twitter Operations out of callback context");
        }
        this.twitterOAuthService = twitterOAuthService;
        this.oauthService = twitterOAuthService.getOAuthService();

    }

    @PostConstruct
    public void build() {
        verifier = new Verifier(oauthVerifier);
        Token requestToken = new Token(oauthToken, twitterOAuthService.getConsumerSecretKey());
        accessToken = oauthService.getAccessToken(requestToken, verifier);
        initProfile();
    }

    private void initProfile() {
        twitterProfile = twitterOAuthService.signRequest("http://api.twitter.com/1/account/verify_credentials.json",
                         accessToken, TwitterProfile.class);
    }

    public TwitterProfile getProfile() {
        return twitterProfile;
    }
}
