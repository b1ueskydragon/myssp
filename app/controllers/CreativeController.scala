package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.mvc._
import services.CreativeService

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class CreativeController @Inject()(
  cc: ControllerComponents,
  ws: WSClient,
  service: CreativeService)
  (implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def creative = Action {

    // 入札額(ランダム生成)を一緒に送信して, maxValue を選ぶ
    def futureRes(dspId: Long): Future[WSResponse] =
      ws.url(s"http://localhost:9000/request/$dspId").get

    val dspCreatives = service.creatives.keySet.map(dspId =>
      Await.result(futureRes(dspId), Duration.Inf)).toSeq

    val winnerId = service.auction(dspCreatives).body

    Ok(views.html.creative(winnerId))

  }

}
