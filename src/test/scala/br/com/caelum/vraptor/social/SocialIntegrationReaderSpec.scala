package br.com.caelum.vraptor.social

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SocialIntegrationReaderSpec extends FlatSpec with ShouldMatchers {

  behavior of "reading xml"

  it should "ignoring missing fields" in {
    val someXML = """{"name":"teste","email":"email"}"""
    val user = new SocialIntegrationReader().from(someXML.toString,classOf[FakeUser])
    user.name should be === "teste"
  }

  it should "read all fields" in {
    val someXML = """ {"name":"teste","email":"email"}"""
    val user = new SocialIntegrationReader().from(someXML.toString,classOf[FakeUser2])
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