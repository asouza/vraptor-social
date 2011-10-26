package br.com.caelum.vraptor.social.facebook

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import br.com.caelum.vraptor.social.SocialIntegrationReader

class FacebookProfileSpec extends FlatSpec with ShouldMatchers {

  val jsonProfile = """{"id":"100001209838248","name":"Alberto Souza","first_name":"Alberto","last_name":"Souza","link":"http:\/\/www.facebook.com\/profile.php?id=100001209838248",
                      "hometown":{"id":"111386048885164","name":"Salvador, Bahia, Brazil"},
                      "location":{"id":"112047398814697","name":"S\u00e3o Paulo, Brazil"},
                      "work":[{"employer":{"id":"113719255307782","name":"Caelum"},
                      "start_date":"0000-00","end_date":"0000-00"}],
                      "education":[{"school":{"id":"114692575214359","name":"Col\u00e9gio Anchieta"},
                      "year":{"id":"194878617211512","name":"2002"},
                      "type":"High School"},
                      {"school":{"id":"108403465858521","name":"UNIFACS"},"year":{"id":"137616982934053","name":"2006"},"type":"College"},
                      {"school":{"id":"113719255307782","name":"Caelum"},"type":"College"}],
                      "gender":"male","timezone":-2,"locale":"pt_BR","languages":[{"id":"108439959180421","name":"Brazilian Portuguese"},
                      {"id":"103999719637010","name":"American English"}],"verified":true,"updated_time":"2011-10-09T15:23:14+0000"}"""


  behavior of "reading some facebook information"

  it should "getOAuthService needed information" in {
      val profile = new SocialIntegrationReader().from(jsonProfile,classOf[FacebookProfile])
      profile.getFirstName should be === "Alberto"
      profile.getId should be === "100001209838248"
      profile.getLink should be === """http://www.facebook.com/profile.php?id=100001209838248"""

  }
}