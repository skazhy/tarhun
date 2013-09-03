package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def allTags : List[String] = List("wat", "win")

  def allImages : Map[String, List[String]] = {
      Map("win" -> List(
        "http://i.imgur.com/iOjD0q1.png",
        "http://i.imgur.com/KdxWrIZ.png"
      ))
  }

  def imagesForTag(tag: String) : List[String] = {
      allImages.getOrElse(tag, List())
  }

  def index = Action {
    val tags = allTags
    val images = allImages.map(_._2).flatten.toList
    Ok(views.html.index(tags, images))
  }

  def tag(tag: String) = Action {
    val tags = allTags
    val images = imagesForTag(tag)
    Ok(views.html.tag(tag, tags, images))
  }

}
