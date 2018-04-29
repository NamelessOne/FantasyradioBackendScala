package models

import javax.inject._
import anorm._
import anorm.SqlParser._
import play.api.db._

case class CurrentStreamInformation(id: Long, imageURL: String, about: String)

class CurrentStreamInformationRepo @Inject()(db: DBApi) {
  val dbName = "default"

  def all(): List[CurrentStreamInformation] = db.database(dbName).withConnection { implicit c =>
    SQL("select * from public.\"CurrentStreamInformation\"").as(simple *)
  }

  private[models] val simple = {
    get[Long]("CurrentStreamInformation.id") ~ str("CurrentStreamInformation.imageURL") ~ str("CurrentStreamInformation.about") map {
      case id ~ imageURL ~ about => CurrentStreamInformation(id, imageURL, about)
    }
  }
}