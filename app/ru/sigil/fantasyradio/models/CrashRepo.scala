package ru.sigil.fantasyradio.models

import anorm.SqlParser.{get, str}
import anorm.{SQL, ~}
import javax.inject.Inject
import play.api.db.DBApi

case class Crash(detail: String, id: Long = 0)

class CrashRepo @Inject()(db: DBApi) {
  val dbName = "default"

  private[models] val info = {
    get[Long]("crash_report.id") ~
      str("crash_report.detail") map {
      case id ~ detail  => Crash(detail, id)
    }
  }

  def save(entity: Crash) {
    db.database(dbName).withConnection { implicit c =>
      SQL("insert into public.crash_report (detail) values (to_json({detail}))").on(
        'detail -> entity.detail,
      ).executeInsert()
    }
  }
}
