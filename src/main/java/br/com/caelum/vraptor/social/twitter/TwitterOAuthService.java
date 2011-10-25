package br.com.caelum.vraptor.social.twitter;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.Operations;
import br.com.caelum.vraptor.social.SocialService;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

import javax.annotation.PostConstruct;

@Component
@ApplicationScoped
public class TwitterOAuthService implements SocialService {

    private Environment environment;
    private OAuthService service;

    public TwitterOAuthService(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init(){
        this.service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey(environment.get("twitter.consumer_key")).
                apiSecret(getConsumerSecret()).build();
    }


    public OAuthService get() {
        return service;
    }

    @Override
    public String getConsumerSecret() {
        return environment.get("twitter.consumer_secret_key");
    }

    public Operations getOperations(){
        return new Operations(this);
    }

}
