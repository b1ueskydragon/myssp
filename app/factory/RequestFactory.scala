package factory

import javax.inject.{Inject, Singleton}
import play.api.libs.ws.{WSClient, WSRequest}

@Singleton
class RequestFactory @Inject()(ws: WSClient) {

  def fakeRequests: Seq[WSRequest] = Seq(
    ws.url(s"http://localhost:9000/request/3"),
    ws.url(s"http://localhost:9000/request/1"),
    ws.url(s"http://localhost:9000/request/2"), // dsp_id 2 is NOT deliverable
    ws.url(s"http://localhost:9000/request/4")
  )

}
