package jenkins.model

import play.api.libs.json.Json

case class Job(name: String, url: String)

object Job {
  implicit val format = Json.format[Job]
}
