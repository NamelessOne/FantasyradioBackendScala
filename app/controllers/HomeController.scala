package controllers

import javax.inject._
import models.CurrentStreamInformationRepo
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, currentStreamInformationRepo: CurrentStreamInformationRepo) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    var t = currentStreamInformationRepo.all()
    Ok(views.html.index() + "fuuuu" + t(0).imageURL)
  }
}
