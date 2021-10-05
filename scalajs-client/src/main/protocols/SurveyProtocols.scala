
import java.time.LocalDateTime
import scala.scalajs.js

object SurveyProtocols {
  trait SurveyMessage extends js.Object {
    val id: Int
    val createdAt: LocalDateTime
    val phone: String
  }

  case class SurveyMessage(phone: String, text: String, date: LocalDateTime)
}