package jenkins

import org.scalatest.{Matchers, FlatSpec}
import jenkins.model.JobDetails

class JenkinsServerSpec extends FlatSpec with Matchers {

  it should "get job details" in {
    // when
    val jobDetails: JobDetails = new JenkinsServer().getJobDetails("maven_template")

    // then
    jobDetails.name should be("maven_template")
  }
}
