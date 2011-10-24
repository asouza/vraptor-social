package br.com.caelum.vraptor.social;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

public class TesteScribe {

    private static final String PROTECTED_RESOURCE_URL = "http://api.twitter.com/1/account/verify_credentials.xml";

    public static void main(String[] args) {
        OAuthService service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey("oL11v44k6AOJlgohemnubQ").
                apiSecret("DfPft0WCumkt9lWFOShVN2GVjRf1oUlbhEduRWwCA").build();
        Token requestToken = service.getRequestToken();
        System.out.println(requestToken.getSecret());

        System.out.println(service.getAuthorizationUrl(requestToken));

        Scanner in = new Scanner(System.in);

        Verifier verifier = new Verifier(in.nextLine());

        Token accessToken = service.getAccessToken(requestToken, verifier);

        System.out.println("(if your curious it looks like this: " + accessToken + " )");

        OAuthRequest request = new OAuthRequest(Verb.GET,PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);

        Response response = request.send();

        System.out.println(response.getBody());

    }
}
