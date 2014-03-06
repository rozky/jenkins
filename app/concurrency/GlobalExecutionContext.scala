package concurrency

import scala.concurrent.ExecutionContext

trait GlobalExecutionContext {
  implicit lazy val defaultExecutionContext = ExecutionContext.Implicits.global
}
