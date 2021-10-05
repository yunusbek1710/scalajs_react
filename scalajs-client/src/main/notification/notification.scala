
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html.Div

import scala.concurrent.duration.{Duration, _}
import scala.language.postfixOps
import scala.scalajs.js.timers._
import scala.util.Random

object notification {
  sealed trait NotificationType {
    val value: String = this.toString.toLowerCase()
  }

  final case object Success extends NotificationType

  final case object Warn extends NotificationType

  final case object Fail extends NotificationType

  final case object Info extends NotificationType
  case class Toast(`type`: NotificationType, icon: String, message: String, title: String)

  case class ToastState(toasts: Map[String, Toast] = Map.empty, duration: Duration)
  class Backend($ : BackendScope[_, ToastState]) {
    def genToastrId: String = Random.alphanumeric.take(5).mkString

    def toastMsg(`type`: NotificationType, icon: String, message: String, title: String): Callback = {
      val id       = genToastrId
      val newToast = Toast(`type`, icon, message, title)
      $.modState { s =>
        deleteToastAfterTimeout(id, s.duration)
        s.copy(toasts = s.toasts + (id -> newToast))
      }
    }

    def deleteToastAfterTimeout(id: String, duration: Duration): SetTimeoutHandle =
      setTimeout(duration.toMillis) {
        $.modState(s => s.copy(toasts = s.toasts - id)).runNow()
      }

    def render(state: ToastState): VdomTagOf[Div] =
      <.div(^.position := "fixed", ^.top := 0.px, ^.right := 0.px, ^.padding := 10.px, ^.zIndex := "9999")(
        state.toasts.values.zipWithIndex
          .toVdomArray { case (toast, index) =>
            <.div(^.key := s"toast$index", ^.cls := s"notification ${toast.`type`.value}")(
              <.section(^.cls := "body")(
                <.i(^.cls := "glyphicon " + toast.icon),
                <.span(^.cls := "title")(toast.title),
                <.p(^.cls := "message")(toast.message)))
          }.when(state.toasts.nonEmpty))
  }

  private val component =
    ScalaComponent
      .builder[Int]
      .initialStateFromProps(p => ToastState(duration = p seconds))
      .renderBackend[Backend]
      .build

  private val toastRef = Ref.toScalaComponent(component)

  private def toast(`type`: NotificationType, icon: String, message: String, title: String): CallbackTo[Unit] =
    toastRef.get
      .map(_.backend.toastMsg(`type`, icon, message, title))
      .getOrElse(Callback.empty)
      .flatten

  def render(duration: Int = 6): Unmounted[Int, ToastState, Backend] = toastRef.component(duration)

  def warning(message: String): Callback =
    toast(Warn, "glyphicon-exclamation-sign", message, "Notification")

  def info(message: String): Callback =
    toast(Info, "glyphicon-info-sign", message, "Information")

  def success(message: String): Callback =
    toast(Success, "glyphicon-ok", message, "Successful")

  def error(message: String): Callback =
    toast(Fail, "glyphicon-warning-sign", message, "Error")

}
