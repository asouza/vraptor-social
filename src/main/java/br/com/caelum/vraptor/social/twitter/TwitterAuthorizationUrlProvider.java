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
public class TwitterAuthorizationUrlProvider {

    private Environment environment;
    private OAuthService service;

    public TwitterAuthorizationUrlProvider(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init(){
        this.service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey(environment.get("twitter.consumer_key")).
                apiSecret(environment.get("twitter.consumer_secret_key")).build();
    }

    public String getUrl(){
        return service.getAuthorizationUrl(service.getRequestToken());
    }

}