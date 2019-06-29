package models

import javax.inject._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

// TODO cache data and scheduling
@Singleton
class CreativeRepository @Inject()(implicit ec: ExecutionContext) {

  private val db = Database.forConfig("db.default")

  private def findStatus(id: Long) = db.run(sql"SELECT is_deliverable FROM creative where id = $id".as[String])

  def deliveryStatus(id: Long): String = Await.result(findStatus(id), Duration.Inf).head

}
