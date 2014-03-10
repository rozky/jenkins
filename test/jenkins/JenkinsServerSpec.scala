package jenkins

import org.scalatest.{Matchers, FlatSpec}
import jenkins.model._
import jenkins.model.JobConfig

class JenkinsServerSpec extends FlatSpec with Matchers {

  val jenkinsServer: JenkinsServer = new JenkinsServer()

  it should "create | update | delete job" in {
    // given
    val jobName: String = "test01"

    val config: JobConfig = JobConfig(
      gitUrl = "https://github.com/rozky/nexus-cleaner.git",
      gitBranch = "master",
      parameters = List(
        StringParameter("developer", "developer name", "dev"),
        BooleanParameter("deploy", "should deploy", true),
        ChoiceParameter("qa", "environment", List("qa1", "qa2"))),
      buildWrappers = Some(List(AnsiColorBuildWrapper(), BuildNameSetterBuildWrapper()))
    )

    // when
    jenkinsServer.deleteJobSafely(jobName)
    jenkinsServer.createJob(jobName, config)
    jenkinsServer.updateJob(jobName, config)
    jenkinsServer.deleteJob(jobName)
  }

  it should "get node details" in {
    // when
    val nodeDetails: NodeDetails = jenkinsServer.getNodeDetails

    // then
    nodeDetails.nodeDescription should be("the master Jenkins node")
  }

  it should "get job details" in {
    // when
    val jobDetails: JobDetails = jenkinsServer.getJobDetails("maven_template")

    // then
    jobDetails.name should be("maven_template")
  }

  it should "get view details" in {
    // when
    val viewDetails: ViewDetails = jenkinsServer.getViewDetails("testview")

    // then
    viewDetails.name should be("testview")
  }
}
