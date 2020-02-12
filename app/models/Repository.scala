package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ Future, ExecutionContext }

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
    def * = (title, name, component_attachment, property_template, value_template, datatype, value_transformation, regex, range) <> ((Column.apply _).tupled, Column.unapply)
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
}