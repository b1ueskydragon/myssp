package models

import javax.inject._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

@Singleton
class CreativeRepository(implicit ec: ExecutionContext) {

  private val database = Database.forConfig("db.default")

  val query = sql"SELECT id, name FROM creative WHERE is_deliverable = 'yes'".as[(Long, String)]
  val f = database.run(query)

  val creatives = Await.result(f, Duration.Inf).toMap

}
