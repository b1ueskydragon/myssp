package controllers

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.ExecutionContext

/** Create a (fake) dsp json request to send */
class RequestController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def request(dspId: Long) = Action { // TODO JSON request for accept additional info ( e.g. bid )
    Ok(views.html.request(dspId))
  }

}
