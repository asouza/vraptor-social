package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.ScribeSignOperations;
import br.com.caelum.vraptor.social.SocialIntegrationReader;
import br.com.caelum.vraptor.social.SocialService;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;

@Component
@ApplicationScoped
public class TwitterOAuthService implements SocialService {

    private OAuthService service;
    private Environment environment;
    private SocialIntegrationReader socialIntegrationReader;

    public TwitterOAuthService(Environment environment,SocialIntegrationReader socialIntegrationReader) {
        this.environment = environment;
        this.socialIntegrationReader = socialIntegrationReader;
    }

    @PostConstruct
    public void init() {
        this.service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey(environment.get("twitter.consumer_key")).
                apiSecret(getConsumerSecretKey()).callback(environment.get("twitter.callback_url")).build();
    }

    @Override
    public <T> T signRequest(String uri, Token accessToken,Class<T> responseClass) {
        return new ScribeSignOperations(this,socialIntegrationReader).signRequest(uri,accessToken,responseClass);
    }

    public String getConsumerSecretKey() {
        return environment.get("twitter.consumer_secret_key");
    }

    public OAuthService getOAuthService() {
        return this.service;
    }
}
