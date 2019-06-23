package models

import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.MySQLDriver.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

@Singleton
class CreativeRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext) {

  // TODO application.conf or use dbConfigProvider
  val db = Database.forURL(
    "jdbc:mysql://localhost/myssp_db",
    driver = "com.mysql.jdbc.Driver",
    user = "root",
    password = "")

  val query = sql"SELECT id, name FROM creative WHERE is_deliverable = 'yes'".as[(Long, String)]
  val f = db.run(query)
  val creatives = Await.result(f, Duration.Inf).toMap

}
