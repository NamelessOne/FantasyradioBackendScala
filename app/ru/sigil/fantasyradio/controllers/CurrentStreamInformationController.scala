package ru.sigil.fantasyradio.controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ru.sigil.fantasyradio.models.{CurrentStreamInformation, CurrentStreamInformationRepo}

@Singleton
class CurrentStreamInformationController @Inject()(cc: ControllerComponents, currentStreamInformationRepo: CurrentStreamInformationRepo) extends AbstractController(cc) {

  def last() = Action { implicit request: Request[AnyContent] =>
    val gson = Converters.registerDateTime(new GsonBuilder).create
    Ok(gson.toJson(currentStreamInformationRepo.last()))
  }

  def add() = Action { request =>
    val info = new Gson().fromJson(request.body.asJson.get.toString, classOf[CurrentStreamInformation])
    currentStreamInformationRepo.save(info)
    Ok
  }
}

