package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def allTags : List[String] = List("wat", "win")

  def index = Action {
    val tags = allTags
    Ok(views.html.index(tags))
  }

}
