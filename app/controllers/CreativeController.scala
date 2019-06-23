package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.CreativeService

@Singleton
class CreativeController @Inject()(
  cc: ControllerComponents,
  service: CreativeService)
  (implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def creative = Action {
    Ok(views.html.creative(service.getCreatives.mkString(",")))
  }

}
