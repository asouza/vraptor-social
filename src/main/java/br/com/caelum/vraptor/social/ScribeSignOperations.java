package br.com.caelum.vraptor.social;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

public class ScribeSignOperations {

    private SocialService socialService;
    private SocialIntegrationReader socialIntegrationReader;

    public ScribeSignOperations(SocialService socialService, SocialIntegrationReader socialIntegrationReader) {
        this.socialService = socialService;
        this.socialIntegrationReader = socialIntegrationReader;
    }

    public <T> T signRequest(String uri, Token accessToken,Class<T> responseClass) {
        OAuthRequest request = new OAuthRequest(Verb.GET,uri);
        socialService.getOAuthService().signRequest(accessToken, request);
        Response response = request.send();
        return socialIntegrationReader.from(response.getBody(),responseClass);
    }
}
