package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class CSVController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def columns(family: String) = Action { implicit request: Request[AnyContent] =>
    Ok("""title,name,component_attachment,property_template,value_template,datatype,value_transformation
         |Geography,geography,qb:dimension,http://purl.org/linked-data/sdmx/2009/dimension#refArea,http://statistics.data.gov.uk/id/statistical-geography/{geography},string,
         |Date,date,qb:dimension,http://purl.org/linked-data/sdmx/2009/dimension#refPeriod,http://reference.data.gov.uk/id/year/{date},string,
         |Measure Type,measure_type,qb:dimension,http://purl.org/linked-data/cube#measureType,http://gss-data.org.uk/def/measure/{measure_type},string,slugize
         |""".stripMargin).as("text/csv")
  }
}
