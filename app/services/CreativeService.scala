package services

import javax.inject.{Inject, Singleton}
import models.CreativeRepository

@Singleton
class CreativeService @Inject()(repository: CreativeRepository) {
  def getCreatives: Map[Long, String] = repository.creatives
}
