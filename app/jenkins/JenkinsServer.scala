package jenkins

import jenkins.model._

class JenkinsServer(url: String = "http://localhost:8080",
                    username: Option[String] = None,
                    password: Option[String] = None) {

  def getNodeDetails: NodeDetails = {
    JenkinsHttpClient.get[NodeDetails](s"$url/api/json")
  }

  def getJobs: Map[String, Job] = {
    null
  }

  def getJobDetails(name: String): JobDetails = {
    JenkinsHttpClient.get[JobDetails](s"$url/job/$name/api/json")
  }

  def getViewDetails(name: String): ViewDetails = {
    JenkinsHttpClient.get[ViewDetails](s"$url/view/$name/api/json")
  }

  def getBuildDetails(name: String, number: Int): BuildDetails = {
    JenkinsHttpClient.get[BuildDetails](s"$url/job/$name/$number/api/json")
  }
}
