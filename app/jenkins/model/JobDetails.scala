package jenkins.model

import play.api.libs.json.Json

case class JobDetails(name: String, description: String, displayName: String) {

}

object JobDetails {
  implicit val format = Json.format[JobDetails]
}
