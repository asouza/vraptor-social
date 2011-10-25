package br.com.caelum.vraptor.social;

import org.scribe.oauth.OAuthService;

public interface SocialService {
    public OAuthService get();

    public String getConsumerSecret();
}
