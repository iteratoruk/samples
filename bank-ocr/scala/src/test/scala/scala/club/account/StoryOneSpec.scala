package scala.club.account

import org.scalatest.{MustMatchers, WordSpec}

class StoryOneSpec extends WordSpec with MustMatchers {

  val parser = new AccountNumberParser()

  class Example(name: String) {
    val in = getClass.getResourceAsStream(s"/$name.txt")
  }

  "the parser" should {

    "correctly parse the number zero" in new Example("sample_01") {
      parser.parse(in) must be("000000000")
    }

    "correctly parse the number one" in new Example("sample_02"){
      parser.parse(in) must be("111111111")
    }

    "correctly parse the number two" in new Example("sample_03"){
      parser.parse(in) must be("222222222")
    }

    "correctly parse the number three" in new Example("sample_04"){
      parser.parse(in) must be("333333333")
    }

    "correctly parse the number four" in new Example("sample_05"){
      parser.parse(in) must be("444444444")
    }

    "correctly parse the number five" in new Example("sample_06"){
      parser.parse(in) must be("555555555")
    }

    "correctly parse the number six" in new Example("sample_07"){
      parser.parse(in) must be("666666666")
    }

    "correctly parse the number seven" in new Example("sample_08"){
      parser.parse(in) must be("777777777")
    }

    "correctly parse the number eight" in new Example("sample_09"){
      parser.parse(in) must be("888888888")
    }

    "correctly parse the number nine" in new Example("sample_10"){
      parser.parse(in) must be("999999999")
    }

    "correctly parse an account number containing digits 1 to 9" in new Example("sample_11"){
      parser.parse(in) must be("123456789")
    }

  }

}
