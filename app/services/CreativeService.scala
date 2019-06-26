package services

import javax.inject.{Inject, Singleton}
import models.{CreativeRepository, DspCreative}

@Singleton
class CreativeService @Inject()(
  repository: CreativeRepository) {

  def isDeliverable(dspId: String): Boolean = repository.deliveryStatus(dspId.toLong) == "yes"

  def auctionByBid(ads: Seq[DspCreative]): Long = ads.maxBy(_.bid).dspId

}
