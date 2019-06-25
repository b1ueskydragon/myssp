package services

import java.util.Random

import javax.inject.{Inject, Singleton}
import models.CreativeRepository
import play.api.libs.ws.WSResponse

@Singleton
class CreativeService @Inject()(
  repository: CreativeRepository,
  random: Random) {

  def creatives: Map[Long, String] = repository.selectedCreatives

  def getRandomIndex(seed: Int): Int = random.nextInt(seed) + 1

  // TODO random to max
  def auction(dspCreatives: Seq[WSResponse]): WSResponse = {
    dspCreatives(getRandomIndex(dspCreatives.length - 1))
  }

}
