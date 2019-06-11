package ru.sigil.fantasyradio.controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import ru.sigil.fantasyradio.models.{Crash, CrashRepo}

@Singleton
class CrashController @Inject()(cc: ControllerComponents, crashRepo: CrashRepo) extends AbstractController(cc) {
  def add() = Action { request =>
    crashRepo.save(new Crash(request.body.asJson.get.toString))
    Ok
  }
}
