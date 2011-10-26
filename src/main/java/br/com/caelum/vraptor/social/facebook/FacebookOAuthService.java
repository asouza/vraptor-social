package br.com.caelum.vraptor.social.facebook;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.ScribeSignOperations;
import br.com.caelum.vraptor.social.SocialIntegrationReader;
import br.com.caelum.vraptor.social.SocialService;
import br.com.caelum.vraptor.social.twitter.TwitterProfile;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;

@Component
@ApplicationScoped
public class FacebookOAuthService implements SocialService {

    private OAuthService service;
    private Environment environment;
    private SocialIntegrationReader socialIntegrationReader;

    public FacebookOAuthService(Environment environment,SocialIntegrationReader socialIntegrationReader) {
        this.environment = environment;
        this.socialIntegrationReader = socialIntegrationReader;
    }

    @PostConstruct
    public void init() {
        this.service = new ServiceBuilder().provider(FacebookApi.class).
                apiKey(environment.get("facebook.consumer_key")).
                apiSecret(getConsumerSecretKey()).callback(environment.get("facebook.callback_url")).build();
    }

    @Override
    public String getConsumerSecretKey(){
        return environment.get("facebook.consumer_secret_key");
    }

    @Override
    public <T> T signRequest(String uri, Token accessToken,Class<T> responseClass) {
        return new ScribeSignOperations(this,socialIntegrationReader).signRequest(uri,accessToken,responseClass);
    }

    @Override
    public OAuthService getOAuthService(){
        return this.service;
    }

    public String getAuthorizationUrl() {
        return service.getAuthorizationUrl(null);
    }

    public Token getAccessToken(Verifier verifier) {
        return service.getAccessToken(null, verifier);
    }
}
