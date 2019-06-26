package controllers

import java.util.Random

import javax.inject.{Inject, Singleton}
import models.DspCreative
import play.api.Logger
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.mvc._
import services.CreativeService

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class CreativeController @Inject()(
  cc: ControllerComponents,
  ws: WSClient,
  service: CreativeService,
  random: Random)
  (implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  private val logger = Logger.logger

  def creative = Action {

    /**  bidding (dsp) request accepted */
    val res1 = Await.result(ws.url(s"http://localhost:9000/request/1").get, Duration.Inf)
    val res2 = Await.result(ws.url(s"http://localhost:9000/request/2").get, Duration.Inf) // NOT deliverable
    val res3 = Await.result(ws.url(s"http://localhost:9000/request/3").get, Duration.Inf)
    val res4 = Await.result(ws.url(s"http://localhost:9000/request/4").get, Duration.Inf)

    val responses = Seq(res1, res2, res3, res4)

    /** at this time, mocked bid value generated in here. */
    val dspAds = responses.filter(res => service.isDeliverable(res.body))
      .map(res => DspCreative(res.body.toLong, random.nextDouble))

    logger.debug(s"bidders : ${dspAds.mkString(",")}")

    val winnerId = service.maxBidCreativeId(dspAds).toString

    logger.debug(s"winner dsp : $winnerId")

    Ok(views.html.creative(winnerId))

  }

}
