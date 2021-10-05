import ExampleUrls.Survey._
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future

trait ExampleJs {
  def onChatHistory: Future[XMLHttpRequest] = {
    Ajax.get(getAllSurvey)
  }
}
