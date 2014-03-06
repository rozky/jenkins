package jenkins

import jenkins.model.{JobDetails, Job}

class JenkinsServer(url: String = "http://localhost:8080",
                    username: Option[String] = None,
                    password: Option[String] = None) {


  def getJobs: Map[String, Job] = {
    null
  }

  def getJobDetails(name: String): JobDetails = {
    JenkinsHttpClient.get[JobDetails](s"$url/job/$name/api/json").get
  }
}
