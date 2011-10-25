import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

public class TesteScribe {

    private static final String PROTECTED_RESOURCE_URL = "http://api.twitter.com/1/account/verify_credentials.xml";

    public static void main(String[] args) {
        OAuthService service = new ServiceBuilder().provider(TwitterApi.class).
                apiKey("z4QiUg6NAQXUISrr1BCmg").
                apiSecret("iCr7zjDdnRRpTIqhlq2oHRZG1F83CNEVvAJ3w6Y").build();
        System.out.println(service.getClass());
        Token requestToken = service.getRequestToken();
        System.out.println(requestToken.getSecret());

        System.out.println(service.getAuthorizationUrl(requestToken));

        Token requestToken2 = service.getRequestToken();
        System.out.println(requestToken2.getSecret());

        System.out.println(service.getAuthorizationUrl(requestToken2));

//        Scanner in = new Scanner(System.in);
//
//        Verifier verifier = new Verifier(in.nextLine());
//
//        Token accessToken = service.getAccessToken(requestToken, verifier);
//
//        System.out.println("(if your curious it looks like this: " + accessToken + " )");
//
//        OAuthRequest request = new OAuthRequest(Verb.GET,PROTECTED_RESOURCE_URL);
//        service.signRequest(accessToken, request);
//
//        Response response = request.send();
//
//        System.out.println(response.getBody());

    }
}
