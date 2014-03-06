package jenkins.model

import play.api.libs.json.Json


case class ViewDetails(name: String,
                       url: String,
                       description: Option[String],
                       property: List[String],
                       jobs: List[Job]) {
}

object ViewDetails {
  implicit val viewDetailsFormats = Json.format[ViewDetails]
}
