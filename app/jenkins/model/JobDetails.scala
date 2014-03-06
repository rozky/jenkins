package jenkins.model

import play.api.libs.json.Json

// there may be more fields then those displayed here
// check some example job: http://localhost:8080/job/[JOB-NAME]/api/json?pretty=true
case class JobDetails(name: String,
                      description: String,
                      displayName: String,
                      url: String,
                      buildable: Boolean,
                      builds: List[JobDetails.Build],
                      color: String,
                      inQueue: Boolean,
                      keepDependencies: Boolean,
                      lastBuild: Option[JobDetails.Build],
                      lastCompletedBuild: Option[JobDetails.Build],
                      lastFailedBuild: Option[JobDetails.Build],
                      lastStableBuild: Option[JobDetails.Build],
                      lastSuccessfulBuild: Option[JobDetails.Build],
                      lastUnstableBuild: Option[JobDetails.Build],
                      nextBuildNumber: Int,
                      property: List[String], concurrentBuild: Boolean,
                      downstreamProjects: List[String],
                      upstreamProjects: List[String],
                      modules: List[JobDetails.Module]) {
}

object JobDetails {
  implicit val buildFormats = Json.format[Build]
  implicit val moduleFormats = Json.format[Module]
  implicit val jobDetailsFormats = Json.format[JobDetails]

  case class Build(number: Int, url: String)
  case class Module(name: String, url: String, color: String, displayName: String)
}
