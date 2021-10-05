package controllers

import com.typesafe.scalalogging.LazyLogging
import play.api.Configuration
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ScalaJsController @Inject() (
                                    val controllerComponents: ControllerComponents,
                                    val configuration: Configuration,
                                    val indexTemplate: views.html.scala_js.index
                                  )(implicit val ec: ExecutionContext)
  extends BaseController
    with LazyLogging {

  def index: Action[AnyContent] = Action {
    Ok(indexTemplate())
  }
}
