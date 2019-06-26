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

    def futureRes(ads: DspCreative): Future[WSResponse] =
      ws.url(s"http://localhost:9000/request/${ads.dspId}").get

    /**  bidding (dsp) request accepted */
    val res1 = Await.result(futureRes(DspCreative(1, random.nextDouble)), Duration.Inf)
    val res2 = Await.result(futureRes(DspCreative(2, random.nextDouble)), Duration.Inf) // NOT deliverable
    val res3 = Await.result(futureRes(DspCreative(3, random.nextDouble)), Duration.Inf)
    val res4 = Await.result(futureRes(DspCreative(4, random.nextDouble)), Duration.Inf)

    val responses = Seq(res1, res2, res3, res4)

    /** at this time, mocked bid value generated in here. */
    val dsps = responses.filter(res => service.deliveryStatus(res.body) == "yes")
      .map(res => DspCreative(res.body.toLong, random.nextDouble))

    logger.debug(s"bidders : ${dsps.mkString}")

    val i = service.maxBidCreativeId(dsps).toString

    logger.debug(s"winner dsp : $i")

    Ok(views.html.creative(i))

  }

}
