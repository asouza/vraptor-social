package br.com.caelum.vraptor.social.twitter;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class TwitterProfile {
    private String id;
    private String name;
    @XStreamAlias("profile_image_url")
    private String image;
    @XStreamAlias("followers_count")
    private int numberOfFollowers;
    @XStreamAlias("screen_name")
    private String screenName;

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


    public String getScreenName() {
        return screenName;
    }
}
