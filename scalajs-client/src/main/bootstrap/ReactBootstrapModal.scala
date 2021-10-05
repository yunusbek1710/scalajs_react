
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-bootstrap", JSImport.Namespace)
object ReactBootstrapModule extends js.Object {

  @js.native
  trait ModalComponent extends js.Object {
    def Body: js.Any = js.native
    def Title: js.Any = js.native
    def Header: js.Any = js.native
    def Footer: js.Any = js.native
    def Dialog: js.Any = js.native
  }

  def Modal: ModalComponent = js.native

}