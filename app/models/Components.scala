package models

import play.api.libs.json._

case class Component(label: String,
                     description: Option[String],
                     component_type: String,
                     codelist: Option[String])

object Component {
  implicit val componentFormat = Json.format[Component]
}
