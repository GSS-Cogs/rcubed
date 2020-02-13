package models

import play.api.libs.json.Json

case class Config(groupID: String,
                  label: Option[String],
                  baseURL: String)

object Config {
  implicit val configFormat = Json.format[Config]
}