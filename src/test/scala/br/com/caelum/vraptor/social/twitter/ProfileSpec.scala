package br.com.caelum.vraptor.social.twitter

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.mapper.MapperWrapper
import net.vidageek.mirror.dsl.Mirror
import br.com.caelum.vraptor.social.SocialIntegrationReader

class ProfileSpec extends FlatSpec with ShouldMatchers{

  val profileXML = <user>
    <id>29437870</id>
    <name>Alberto Souza</name>
    <screen_name>alberto_souza</screen_name>
    <location>Salvador</location>
    <description>Vamo ajudar todo mundo a arrumar lugar para sair no Brasil todo!!!. Indiqueai!!!!!</description>
    <profile_image_url>http://a3.twimg.com/profile_images/257134529/PHOT0023_normal.JPG</profile_image_url>
    <profile_image_url_https>https://si0.twimg.com/profile_images/257134529/PHOT0023_normal.JPG</profile_image_url_https>
    <url>http://www.indiqueai.com.br</url>
    <protected>false</protected>
    <followers_count>318</followers_count>
    <profile_background_color>C0DEED</profile_background_color>
    <profile_text_color>333333</profile_text_color>
    <profile_link_color>0084B4</profile_link_color>
    <profile_sidebar_fill_color>DDEEF6</profile_sidebar_fill_color>
    <profile_sidebar_border_color>C0DEED</profile_sidebar_border_color>
    <friends_count>211</friends_count>
    <created_at>Tue Apr 07 12:34:34 +0000 2009</created_at>
    <favourites_count>80</favourites_count>
    <utc_offset>-14400</utc_offset>
    <time_zone>Santiago</time_zone>
    <profile_background_image_url>http://a0.twimg.com/images/themes/theme1/bg.png</profile_background_image_url>
    <profile_background_image_url_https>https://si0.twimg.com/images/themes/theme1/bg.png</profile_background_image_url_https>
    <profile_background_tile>false</profile_background_tile>
    <profile_use_background_image>true</profile_use_background_image>
    <notifications>false</notifications>
    <geo_enabled>false</geo_enabled>
    <verified>false</verified>
    <following>false</following>
    <statuses_count>1843</statuses_count>
    <lang>en</lang>
    <contributors_enabled>false</contributors_enabled>
    <follow_request_sent>false</follow_request_sent>
    <listed_count>20</listed_count>
    <show_all_inline_media>false</show_all_inline_media>
    <default_profile>true</default_profile>
    <default_profile_image>false</default_profile_image>
    <is_translator>false</is_translator>
    <status>
      <created_at>Mon Oct 24 00:28:47 +0000 2011</created_at>
      <id>128266651773841410</id>
      <text>Estou achando que s
        &#243;
        eu n
        &#227;
        o conhecia...</text>
      <source>
        &lt;
        a href=
        &quot;
        http://www.tweetdeck.com
        &quot;
        rel=
        &quot;
        nofollow
        &quot; &gt;
        TweetDeck
        &lt;
        /a
        &gt;
      </source>
      <truncated>false</truncated>
      <favorited>false</favorited>
      <in_reply_to_status_id></in_reply_to_status_id>
      <in_reply_to_user_id></in_reply_to_user_id>
      <in_reply_to_screen_name></in_reply_to_screen_name>
      <retweet_count>0</retweet_count>
      <retweeted>false</retweeted>
        <geo/>
        <coordinates/>
        <place/>
        <contributors/>
    </status>
  </user>

  behavior of "parsing the profile xml"

  it should "create the profile object based on returned xml" in {
    val profile = new SocialIntegrationReader().from[TwitterProfile](profileXML.toString)
    profile.getName should be === "Alberto Souza"
    profile.getImage should be === "http://a3.twimg.com/profile_images/257134529/PHOT0023_normal.JPG"
    profile.getId should be === "29437870"
    profile.getNumberOfFollowers should be === 318
  }



}