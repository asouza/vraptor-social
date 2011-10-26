package br.com.caelum.vraptor.social.twitter

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.mapper.MapperWrapper
import net.vidageek.mirror.dsl.Mirror
import br.com.caelum.vraptor.social.SocialIntegrationReader

class TwitterProfileSpec extends FlatSpec with ShouldMatchers {

  val jsonProfile = """{"is_translator":false,"geo_enabled":false,"profile_background_tile":false,
  "profile_background_image_url_https":"https:\/\/si0.twimg.com\/images\/themes\/theme1\/bg.png",
  "protected":false,"location":"Salvador","follow_request_sent":false,"friends_count":211,
  "profile_sidebar_fill_color":"DDEEF6","name":"Alberto Luiz Souza",
  "url":"http:\/\/www.indiqueai.com.br","utc_offset":-14400,
  "profile_sidebar_border_color":"C0DEED",
  "profile_image_url_https":"https:\/\/si0.twimg.com\/profile_images\/257134529\/PHOT0023_normal.JPG",
  "description":"Vamo ajudar todo mundo a arrumar lugar para sair no Brasil todo!!!. Indiqueai!!!!!",
  "following":false,"listed_count":20,"profile_use_background_image":true,
  "status":{"place":null,"retweet_count":0,"in_reply_to_screen_name":null,"geo":null,
  "retweeted":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"coordinates":null,
  "truncated":false,"in_reply_to_user_id_str":null,"created_at":"Tue Oct 25 11:20:49 +0000 2011",
  "contributors":null,"id_str":"128793127275331584",
  "source":"\u003Ca href=\"http:\/\/www.tweetdeck.com\" rel=\"nofollow\"\u003ETweetDeck\u003C\/a\u003E",
  "id":128793127275331584,"in_reply_to_user_id":null,"favorited":false,
  "text":"comecei a ler a  biografia de steve jobs, vamos ver se rola alguma inspira\u00e7\u00e3o por osmose :)"}
  ,"created_at":"Tue Apr 07 12:34:34 +0000 2009","profile_text_color":"333333","screen_name":"alberto_souza",
  "show_all_inline_media":false,"contributors_enabled":false,
  "profile_background_image_url":"http:\/\/a0.twimg.com\/images\/themes\/theme1\/bg.png",
  "id_str":"29437870","notifications":false,"favourites_count":80,"profile_link_color":"0084B4",
  "default_profile_image":false,"statuses_count":1844,"verified":false,"time_zone":"Santiago",
  "id":29437870,"default_profile":true,"lang":"en","profile_background_color":"C0DEED",
  "followers_count":317,"profile_image_url":"http:\/\/a3.twimg.com\/profile_images\/257134529\/PHOT0023_normal.JPG"}"""

  behavior of "parsing the profile xml"

  it should "create the profile object based on returned xml" in {
    val profile = new SocialIntegrationReader().from(jsonProfile,classOf[TwitterProfile])
    profile.getName should be === "Alberto Luiz Souza"
    profile.getImage should be === "http://a3.twimg.com/profile_images/257134529/PHOT0023_normal.JPG"
    profile.getId should be === "29437870"
    profile.getNumberOfFollowers should be === 317
    profile.getScreenName should be === "alberto_souza"
  }


}