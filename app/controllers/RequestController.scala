package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._

import scala.concurrent.ExecutionContext

/** Create a (fake) dsp json request to send */
@Singleton
class RequestController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  // TODO JSON request for accept additional info ( e.g. bid )
  def request(dspId: Long) = Action {
    Ok(views.html.request(dspId))
  }

}
