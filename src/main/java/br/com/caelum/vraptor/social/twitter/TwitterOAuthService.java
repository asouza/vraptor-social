package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;

@Component
@ApplicationScoped
public class TwitterOAuthService {

    private OAuthService service;
    private Environment environment;

    public TwitterOAuthService(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        this.service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey(environment.get("twitter.consumer_key")).
                apiSecret(getConsumerSecretKey()).callback(environment.get("twitter.callback_url")).build();
    }

    public String getConsumerSecretKey(){
        return environment.get("twitter.consumer_secret_key");
    }

    public OAuthService get(){
        return this.service;
    }
}
