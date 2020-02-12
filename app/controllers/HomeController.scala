package controllers

import javax.inject._
import models.Repository
import play.api._
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(repo: Repository,
                               cc: MessagesControllerComponents
                              )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def columns() = Action.async { implicit request: Request[AnyContent] =>
    repo.listColumns().map {
      columns => Ok(views.html.columns(columns))
    }
  }

  def components() = Action.async { implicit request: Request[AnyContent] =>
    repo.listComponents().map {
      components => Ok(views.html.components(components))
    }
  }

}
