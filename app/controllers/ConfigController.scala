package controllers

import controllers.routes
import javax.inject.Inject
import models.{Config, Repository}
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.mvc.{MessagesAbstractController, MessagesControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class ConfigController @Inject()(repo: Repository,
                                 cc: MessagesControllerComponents
                                )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  val configForm: Form[CreateConfigForm] = Form {
    mapping(
      "label" -> nonEmptyText,
      "base_url" -> nonEmptyText
    )(CreateConfigForm.apply)(CreateConfigForm.unapply)
  }

  def config = Action.async { implicit request =>
    repo.listConfigs().map {
      configs => Ok(views.html.config(configs, configForm))
    }
  }

  def addConfig = Action.async { implicit request =>
    configForm.bindFromRequest.fold(
      errorForm => {
        repo.listConfigs().map {
          configs => Ok(views.html.config(configs, errorForm))
        }
      },
      configForm => {
        val config = Config(
          configForm.name.replaceAll("[^\\w ]", "").replace(" ", "-").toLowerCase,
          Some(configForm.name), configForm.baseURL)
        repo.loadNewConfig(config).map { _ =>
          Redirect(routes.ConfigController.config).flashing("success" -> "config.loaded")
        }
      }
    )
  }
}

case class CreateConfigForm(name: String, baseURL: String)
