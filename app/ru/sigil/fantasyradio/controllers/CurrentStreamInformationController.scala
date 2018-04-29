package ru.sigil.fantasyradio.controllers

import com.google.gson.Gson
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import ru.sigil.fantasyradio.models.{CurrentStreamInformation, CurrentStreamInformationRepo}

@Singleton
class CurrentStreamInformationController @Inject()(cc: ControllerComponents, currentStreamInformationRepo: CurrentStreamInformationRepo) extends AbstractController(cc) {

  def last() = Action { implicit request: Request[AnyContent] =>
    Ok(currentStreamInformationRepo.last() + "")
  }

  def add() = Action { request =>
    val test = request.body.asJson.get.toString
    val info = new Gson().fromJson(test, classOf[CurrentStreamInformation])
    currentStreamInformationRepo.save(info)
    Ok
  }
}

