package br.com.caelum.vraptor.social;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

//#TODO think about how to remove  scribe dependency from interface
public interface SocialService {

    OAuthService getOAuthService();

    String getConsumerSecretKey();

    <T> T signRequest(String uri,Token accessToken, Class<T> responseClass);
}
