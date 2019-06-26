package services

import javax.inject.{Inject, Singleton}
import models.{CreativeRepository, DspCreative}

@Singleton
class CreativeService @Inject()(
  repository: CreativeRepository) {

  def deliveryStatus(dspId: String): String = repository.status(dspId.toLong)

  def maxBidCreativeId(ads: Seq[DspCreative]): Long = ads.maxBy(_.bid).dspId

}

