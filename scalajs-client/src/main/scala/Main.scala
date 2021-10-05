
import org.scalajs.dom.ext.AjaxException
import org.scalajs.dom.window

object Main extends App
  with ExampleJs {
  println("Hello world!")

  def logout(): Unit = {
    window.alert("Your session has been expired!\nPlease log in.")
    window.location.href = ExampleUrls.Common.logout
  }

  def handleError: PartialFunction[Throwable, Unit] = {
    case error: AjaxException =>
      if (error.xhr.status == 401)
        logout()
      else if ((error.xhr.status == 200 || error.xhr.status == 400) && error.xhr.responseText.isEmpty)
        window.alert(error.xhr.responseText)
      else
        window.alert("Something went wrong ! Please try again.")
    case other                =>
      println("Error occurred:" + other.getMessage)
      window.alert("Something went wrong ! Please try again." + other.getMessage)
  }
}
