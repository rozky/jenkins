package jenkins

import jenkins.model._
import java.net.URLEncoder
import jenkins.http.{Http404Exception, JenkinsHttpClient}

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

  def createJob(name: String, jobConfig: JobConfig) {
    val body: String = views.xml.JenkinsJobs.job(jobConfig).body.trim
    JenkinsHttpClient.post(s"$url/createItem?name=${URLEncoder.encode(name, "UTF-8")}", body)
  }

  def updateJob(name: String, jobConfig: JobConfig) {
    val body: String = views.xml.JenkinsJobs.job(jobConfig).body.trim
    JenkinsHttpClient.post(s"$url/job/${URLEncoder.encode(name, "UTF-8")}/config.xml", body)
  }

  def deleteJob(name: String) {
    JenkinsHttpClient.post(s"$url/job/${URLEncoder.encode(name, "UTF-8")}/doDelete")
  }

  def deleteJobSafely(name: String) {
    try {
      JenkinsHttpClient.post(s"$url/job/${URLEncoder.encode(name, "UTF-8")}/doDelete")
    } catch {
      case Http404Exception => println(s"job $name was not deleted as it doesn't exist")
    }
  }
}
