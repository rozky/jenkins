package jenkins

import scala.reflect.runtime.universe._
import dispatch._
import com.ning.http.client.Response
import dispatch.Req
import play.api.libs.json.{Reads, Json}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import concurrency.GlobalExecutionContext

object JenkinsHttpClient extends GlobalExecutionContext {

  def get[A](requestURL: String)(implicit reader: Reads[A]): A = {
    val request: Req = url(requestURL).GET.addHeader("Content-type", "application/json")

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
}
