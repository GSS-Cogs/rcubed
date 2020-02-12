package models

import play.api.libs.json._

case class Column(title: String,
                  name: String,
                  component_attachment: Option[String],
                  property_template: String,
                  value_template: Option[String],
                  datatype: Option[String],
                  value_transformation: Option[String],
                  regex: Option[String],
                  range: Option[String])

object Column {
  implicit val columnFormat = Json.format[Column]
}
