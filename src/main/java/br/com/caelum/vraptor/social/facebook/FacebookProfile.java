package br.com.caelum.vraptor.social.facebook;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class FacebookProfile {
    private String id;
    @XStreamAlias("first_name")
    private String firstName;
    @XStreamAlias("last_name")
    private String lastName;
    private String link;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLink() {
        return link;
    }

    public String getId() {

        return id;
    }
}
