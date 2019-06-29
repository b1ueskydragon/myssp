package controllers

import factory.RequestFactory
import javax.inject.{Inject, Singleton}
import models.DspCreative
import play.api.Logger
import play.api.libs.ws.WSResponse
import play.api.mvc._
import services.CreativeService

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class CreativeController @Inject()(
  cc: ControllerComponents,
  service: CreativeService,
  dspRequest: RequestFactory) extends AbstractController(cc) {

  private val logger = Logger.logger
  private val random = new java.util.Random()

  /** bidding (dsp) request accepted */
  def requestsToResponses: Seq[WSResponse] = dspRequest.fakeRequests.map(req => Await.result(req.get, Duration.Inf))

  def winnerFromRes(responses: Seq[WSResponse]): String = {
    /** at this time, mocked bid value generated in here. */
    val dspAds = responses.filter(res => service.isDeliverable(res.body))
      .map(res => DspCreative(res.body.toLong, random.nextDouble))

    logger.debug(s"bidders : ${dspAds.mkString(",")}")
    service.auctionByBid(dspAds).toString
  }

  def creative = Action {
    val winnerId = winnerFromRes(requestsToResponses)

    logger.debug(s"winner dsp : $winnerId")
    Ok(views.html.creative(winnerId))
  }

}
