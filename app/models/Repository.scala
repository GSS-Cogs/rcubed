package models

import java.net.{URI, URL}

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import com.github.tototoshi.csv._

import scala.io.Source

@Singleton
class Repository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class ColumnTable(tag: Tag) extends Table[Column](tag, "columns") {
    def title = column[String]("title")
    def name = column[String]("name", O.PrimaryKey)
    def component_attachment = column[Option[String]]("component_attachment")
    def property_template = column[String]("property_template")
    def value_template = column[Option[String]]("value_template")
    def datatype = column[Option[String]]("datatype")
    def value_transformation = column[Option[String]]("value_transformation")
    def regex: Rep[Option[String]] = column[Option[String]]("regex")
    def range: Rep[Option[String]] = column[Option[String]]("range")
    def group_id = column[String]("group_id")
    def * = (title, name, component_attachment, property_template, value_template,
      datatype, value_transformation, regex, range, group_id) <> ((Column.apply _).tupled, Column.unapply)
  }

  private val columns = TableQuery[ColumnTable]

  def listColumns(): Future[Seq[Column]] = db.run {
    columns.result
  }

  private class ComponentTable(tag: Tag) extends Table[Component](tag, "components") {

    def label = column[String]("Label", O.PrimaryKey)
    def description = column[Option[String]]("Description")
    def component_type = column[String]("Component Type")
    def codelist = column[Option[String]]("Codelist")
    def * = (label, description, component_type, codelist) <> ((Component.apply _).tupled, Component.unapply)
  }

  private val components = TableQuery[ComponentTable]

  def listComponents(): Future[Seq[Component]] = db.run {
    components.result
  }

  private class ConfigTable(tag: Tag) extends Table[Config](tag, "table2qb_config") {

    def groupID = column[String]("group_id", O.PrimaryKey)
    def label = column[Option[String]]("label")
    def baseURL = column[String]("base_url")
    def * = (groupID, label, baseURL) <> ((Config.apply _).tupled, Config.unapply)
  }

  private val configs = TableQuery[ConfigTable]

  def createConfig(groupID: String, name: String, baseURL: String): Future[Int] = db.run {
    configs += Config(groupID, Some(name), baseURL)
  }

  def loadNewConfig(config: Config) = {
    val base = new URI(config.baseURL)
    val columnsReader = CSVReader.open(Source.fromURL(base.resolve("columns.csv").toURL))
    val componentsReader = CSVReader.open(Source.fromURL(base.resolve("components.csv").toURL))
    val rows = columnsReader.iteratorWithHeaders.map { row =>
      Column(row("title"), row("name"), row get "component_attachment", row("property_template"),
        row get "value_template", row get "datatype", row get "value_transformation", row get "regex",
        row get "range", config.groupID)
    }
    db.run(DBIO.seq(
      columns ++= rows.to(Iterable),
      configs += config
    ))
  }

}