package services

import models.{CreativeRepository, DspCreative}
import org.scalatest.FunSpec
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.ExecutionContext.Implicits.global

class CreativeServiceSpec extends FunSpec with MockitoSugar {

  describe("isDeliverable") {

    val dspId = "1"

    it("returns true if deliverable status is `yes`") {
      val repository = new CreativeRepository {
        override def deliveryStatus(id: Long): String = "yes"
      }

      val service = new CreativeService(repository)

      assert(service.isDeliverable(dspId))
    }

    it("returns false if deliverable status is `no`") {
      val repository = new CreativeRepository {
        override def deliveryStatus(id: Long): String = "no"
      }

      val service = new CreativeService(repository)

      assert(!service.isDeliverable(dspId))
    }

  }

  describe("auctionByBid") {

    val service = new CreativeService(mock[CreativeRepository])

    val ads = Seq(
      DspCreative(9, 0.7445461555450325),
      DspCreative(3, 0.8753479008340748),
      DspCreative(6, 0.8557899853210991)
    )

    it("returns dspId that has max value of bid among the group") {
      assert(service.auctionByBid(ads) === 3)
    }

  }

}
