package br.com.caelum.vraptor.social;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class Operations {

    private OAuthService oAuthService;
    private SocialService socialService;

    public Operations(SocialService socialService){
        this.socialService = socialService;
        this.oAuthService = socialService.get();
    }

    public Token getAccessToken(String rawToken, Verifier verifier) {
        return oAuthService.getAccessToken(new Token(rawToken,socialService.getConsumerSecret()), verifier);
    }

    public Response signRequest(String rawToken, OAuthRequest request) {
        oAuthService.signRequest(new Token(rawToken,socialService.getConsumerSecret()), request);
        return request.send();
    }

    public String getAuthorizationUrl() {
        return oAuthService.getAuthorizationUrl(oAuthService.getRequestToken());
    }
}
