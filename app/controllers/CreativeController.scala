package controllers

import java.util.Random

import factory.RequestFactory
import javax.inject.{Inject, Singleton}
import models.DspCreative
import play.api.Logger
import play.api.libs.ws.{WSRequest, WSResponse}
import play.api.mvc._
import services.CreativeService

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class CreativeController @Inject()(
  cc: ControllerComponents,
  service: CreativeService,
  dspRequest: RequestFactory,
  random: Random) extends AbstractController(cc) {

  private val logger = Logger.logger

  private def toResponse(req: WSRequest): WSResponse = Await.result(req.get, Duration.Inf)

  def creative = Action {

    /** bidding (dsp) request accepted */
    val responses = dspRequest.fakeRequests.map(toResponse)

    /** at this time, mocked bid value generated in here. */
    val dspAds = responses.filter(res => service.isDeliverable(res.body))
      .map(res => DspCreative(res.body.toLong, random.nextDouble))

    logger.debug(s"bidders : ${dspAds.mkString(",")}")

    val winnerId = service.auctionByBid(dspAds).toString

    logger.debug(s"winner dsp : $winnerId")

    Ok(views.html.creative(winnerId))

  }

}
