
import cielo.assist.Main.logout
import cielo.assist.notification.Notification
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.Ajax
import japgolly.scalajs.react.extra.Ajax.Step2
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.AjaxException

import scala.scalajs.js

trait AjaxImplicits {

  def onError(exception: AjaxException): Callback = exception match {
    case error if error.xhr.status == 401 =>
      logout()
    case error if (error.xhr.status == 200 || error.xhr.status == 400) && error.xhr.responseText.nonEmpty =>
      Notification.error(error.xhr.responseText)
    case _ =>
      Notification.error("Something went wrong! Please try again.")
  }

  def post(url: String, body: js.Any): Step2 =
    Ajax("POST", url)
      .setRequestContentTypeJsonUtf8
      .send(body)

  def get(url: String): Step2 =
    Ajax("GET", url)
      .setRequestContentTypeJsonUtf8
      .send

  implicit class Step2Ops(val step2: Step2) {
    def isSuccessFull: XMLHttpRequest => Boolean = xhr =>
      xhr.status == 200 && xhr.getResponseHeader("Content-Type") == "application/json"

    final def fail(onFailure: AjaxException => Callback): Step2 =
      step2.onComplete(xhr =>
        Callback.unless(isSuccessFull(xhr))(onFailure(AjaxException(xhr))))

    final def done(onSuccess: XMLHttpRequest => Callback): Step2 =
      step2.onComplete(xhr =>
        Callback.when(isSuccessFull(xhr))(onSuccess(xhr)))
  }

}
