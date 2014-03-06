package jenkins.model

import play.api.libs.json.Json

// mapping for http://localhost:8080/api/json?pretty=true
case class NodeDetails(mode: String,
                       nodeDescription: String,
                       nodeName: String,
                       numExecutors: Int,
                       description: Option[String],
                       primaryView: NodeDetails.View,
                       jobs: List[Job],
                       views: List[NodeDetails.View]) {
}

object NodeDetails {
  implicit val viewFormats = Json.format[View]
  implicit val nodeDetailsFormats = Json.format[NodeDetails]

  case class View(name: String, url: String)
}
