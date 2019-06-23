package controllers

import javax.inject._
import play.api.mvc._
import services.CreativeService
import scala.concurrent.ExecutionContext.Implicits.global // TODO erase

@Singleton
class HomeController @Inject()(
  cc: ControllerComponents,
  creativeService: CreativeService)
  (implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
