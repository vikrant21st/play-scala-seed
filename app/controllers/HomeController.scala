package controllers

import javax.inject._

import play.api._
import play.api.mvc._

//import scala.collection.mutable
import scala.collection.mutable.HashMap

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(createLinks()))
  }

  def createLinks(): HashMap[String, String] = {
    val link = HashMap("open" -> "/first")
    link
  }
}
