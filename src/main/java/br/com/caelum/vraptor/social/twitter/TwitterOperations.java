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
    private SocialIntegrationReader socialIntegrationReader;
    private String oauthToken;
    private String oauthVerifier;
    private OAuthService oauthService;
    private Verifier verifier;
    private Token requestToken;
    private Token accessToken;
    private TwitterProfile twitterProfile;

    public TwitterOperations(HttpServletRequest request, TwitterOAuthService twitterOAuthService,
                              SocialIntegrationReader socialIntegrationReader) {
        this.oauthToken = request.getParameter("oauth_token");
        this.oauthVerifier = request.getParameter("oauth_verifier");
        if(oauthToken==null || oauthVerifier == null){
            throw new IllegalStateException("You should not try to use Twitter Operations out of callback context");
        }
        this.twitterOAuthService = twitterOAuthService;
        this.socialIntegrationReader = socialIntegrationReader;
        this.oauthService = twitterOAuthService.get();

    }

    @PostConstruct
    public void build() {
        verifier = new Verifier(oauthVerifier);
        requestToken = new Token(oauthToken, twitterOAuthService.getConsumerSecretKey());
        accessToken = oauthService.getAccessToken(requestToken, verifier);
        initProfile();
    }

    private void initProfile() {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.xml");
        oauthService.signRequest(accessToken, request);
        Response response = request.send();
        twitterProfile = socialIntegrationReader.from(response.getBody());
    }

    public TwitterProfile getProfile(){
        return twitterProfile;
    }
}
