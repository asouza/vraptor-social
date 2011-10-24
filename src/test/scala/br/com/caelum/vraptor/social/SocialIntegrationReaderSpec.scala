package br.com.caelum.vraptor.social

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SocialIntegrationReaderSpec extends FlatSpec with ShouldMatchers {

  behavior of "reading xml"

  it should "ignoring missing fields" in {
    val someXML = <br.com.caelum.vraptor.social.FakeUser>
      <name>teste</name> <email>email</email>
    </br.com.caelum.vraptor.social.FakeUser>
    val user = new SocialIntegrationReader[FakeUser].from(someXML.toString)
    user.name should be === "teste"
  }

  it should "read all fields" in {
    val someXML = <br.com.caelum.vraptor.social.FakeUser2>
      <name>teste</name> <email>email</email>
    </br.com.caelum.vraptor.social.FakeUser2>
    val user = new SocialIntegrationReader[FakeUser2].from(someXML.toString)
    user.name should be === "teste"
    user.email should  be === "email"
  }
}

class FakeUser {
  var name:String = _
}

class FakeUser2{
  var name:String = _
  var email:String = _
}