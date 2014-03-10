package jenkins.http

import dispatch._
import com.ning.http.client.Response
import dispatch.Req
import play.api.libs.json.{Reads, Json}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import concurrency.GlobalExecutionContext
import play.api.http.MimeTypes
import org.apache.commons.lang3.StringUtils

object JenkinsHttpClient extends GlobalExecutionContext {

  def get[A](requestURL: String)(implicit reader: Reads[A]): A = {
    val request: Req = url(requestURL).GET.addHeader("Content-type", MimeTypes.JSON)

    val response: dispatch.Future[Response] = Http(request)
    val result: Future[A] = response.map(resp => {
      if (resp.getStatusCode / 100 == 2) {
        val body: String = resp.getResponseBody
        println("content = " + body)
        Json.parse(body).as[A]
      } else {
        throw new Exception(
          s"Http request has failed with the status '${resp.getStatusCode} ${resp.getStatusText}'. Body: ${resp.getResponseBody}")
      }
    })
    
    Await.result(result, Duration.Inf)
  }

  def post(requestURL: String, body: String) = {
    val request: Req = url(requestURL).POST
      .addHeader("Content-type", MimeTypes.XML)
      .setBody(body)

    execute(request)
  }

  def post(requestURL: String) = {
    val request: Req = url(requestURL).POST
      .addHeader("Content-type", MimeTypes.XML)

    execute(request)
  }

  private def execute(request: Req) {
    val response: dispatch.Future[Response] = Http(request)
    val result = response.map(resp => {
      if (resp.getStatusCode / 100 == 2 || resp.getStatusCode == 302) {
        if (StringUtils.isNotBlank(resp.getResponseBody)) {
          println("response body: " + resp.getResponseBody)
        }
      } else if (resp.getStatusCode == 404) {
        throw Http404Exception
      } else {
        throw new Exception(
          s"Http request has failed with the status '${resp.getStatusCode} ${resp.getStatusText}'. Body: ${resp.getResponseBody}")
      }
    })

    Await.result(result, Duration.Inf)
  }
}
