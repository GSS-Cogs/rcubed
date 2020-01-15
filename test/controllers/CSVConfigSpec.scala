package controllers

import play.api.libs.ws.WSClient
import play.api.test._

class CSVConfigSpec extends PlaySpecification {

  "CSV configuration" should {
    "Serve trade 'columns.csv'" in new WithServer {
      val ws = app.injector.instanceOf[WSClient]
      val response = await(ws.url(s"http://localhost:$port/csv/trade/columns.csv").get)
      response.status must equalTo(OK)
    }
  }
}
