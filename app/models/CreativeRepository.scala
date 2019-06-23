package models

import javax.inject._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

@Singleton
class CreativeRepository @Inject()(implicit ec: ExecutionContext) {

  private val db = Database.forConfig("db.default")
  private val query = sql"SELECT id, name FROM creative WHERE is_deliverable = 'yes'".as[(Long, String)]
  private val f = db.run(query)

  val creatives: Map[Long, String] = Await.result(f, Duration.Inf).toMap

}
