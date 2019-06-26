package models

import javax.inject._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

@Singleton
class CreativeRepository @Inject()(implicit ec: ExecutionContext) {

  private val db = Database.forConfig("db.default")

  private def query(id: Long) = sql"SELECT is_deliverable FROM creative where id = $id".as[String]

  private def f(id: Long) = db.run(query(id))

  def status(id: Long) = Await.result(f(id), Duration.Inf).head
}
