package models

import play.api.libs.json.Json

/** Table of Creative
  *
  * @param id            a unique creative id
  * @param name          file name of creative
  * @param isDeliverable is this creative is deliverable
  */
case class Creative(id: Long, name: String, isDeliverable: Boolean)

object Creative {
  implicit val creativeFormat = Json.format[Creative]
}
