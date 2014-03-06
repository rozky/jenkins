package jenkins.model

import play.api.libs.json.Json

// mapping for http://localhost:8080/job/[JOB-NAME]/[BUILD-NO]/api/json?pretty=true
case class BuildDetails(id: String,
                        number: Int,
                        url: String,
                        keepLog: Boolean,
                        result: String, // SUCCESS
                        duration: Long,
                        estimatedDuration: Long,
                        fullDisplayName: String,
                        building: Boolean,
                        timestamp: Long,
                        culprits: List[String]) {
}

object BuildDetails {
  implicit val buildFormat = Json.format[BuildDetails]
}
