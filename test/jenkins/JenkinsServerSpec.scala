package jenkins

import org.scalatest.{Matchers, FlatSpec}
import jenkins.model.{NodeDetails, ViewDetails, JobDetails}

class JenkinsServerSpec extends FlatSpec with Matchers {

  it should "get node details" in {
    // when
    val nodeDetails: NodeDetails = new JenkinsServer().getNodeDetails

    // then
    nodeDetails.nodeDescription should be("the master Jenkins node")
  }

  it should "get job details" in {
    // when
    val jobDetails: JobDetails = new JenkinsServer().getJobDetails("maven_template")

    // then
    jobDetails.name should be("maven_template")
  }

  it should "get view details" in {
    // when
    val viewDetails: ViewDetails = new JenkinsServer().getViewDetails("testview")

    // then
    viewDetails.name should be("testview")
  }
}
