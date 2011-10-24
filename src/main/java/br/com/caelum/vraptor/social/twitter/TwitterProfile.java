package br.com.caelum.vraptor.social.twitter;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
public class TwitterProfile {
    private String id;
    private String name;
    @XStreamAlias("profile_image_url")
    private String image;
    @XStreamAlias("followers_count")
    private int numberOfFollowers;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public String getId() {

        return id;
    }
}
