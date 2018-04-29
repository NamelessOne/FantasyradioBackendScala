package ru.sigil.fantasyradio.models

import javax.inject._
import anorm._
import anorm.SqlParser._
import play.api.db._
import org.joda.time.DateTime

import scala.language.reflectiveCalls

case class CurrentStreamInformation(imageURL: String, about: String, created: DateTime = DateTime.now, id: Long = 0)

class CurrentStreamInformationRepo @Inject()(db: DBApi) {
  val dbName = "default"

  private[models] val info = {
    get[Long]("CurrentStreamInformation.id") ~
      str("CurrentStreamInformation.imageURL") ~
      str("CurrentStreamInformation.about") ~
      get[DateTime] ("CurrentStreamInformation.created") map {
      case id ~ imageURL ~ about ~ created => CurrentStreamInformation(imageURL, about, created, id)
    }
  }

  def all(): List[CurrentStreamInformation] = db.database(dbName).withConnection { implicit c =>
    SQL("select * from public.\"CurrentStreamInformation\"").as(info *)
  }

  def save(entity: CurrentStreamInformation) {
    db.database(dbName).withConnection { implicit c =>
      SQL("insert into public.\"CurrentStreamInformation\" (\"imageURL\", about) values ({imageURL}, {about})").on(
        'imageURL -> entity.imageURL,
        'about -> entity.about
      ).executeInsert()
    }
  }

  def last(): CurrentStreamInformation = db.database(dbName).withConnection { implicit c =>
    SQL("select * from public.\"CurrentStreamInformation\" order by created desc limit 1").as(info.single)
  }
}