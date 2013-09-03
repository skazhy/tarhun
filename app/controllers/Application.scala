package controllers

import scala.util.Random

import play.api._
import play.api.Play.current
import play.api.mvc._
import com.typesafe.plugin.RedisPlugin

object Application extends Controller {

  val pool = Play.application.plugin(classOf[RedisPlugin]).get.sedisPool

  def allTags : List[String] = {
    pool.withClient { client =>
      return client.smembers("tags").toList
    }
  }

  implicit val tags: List[String] = allTags

  def allImages : Map[String, List[String]] = {
    val tags = allTags
    pool.withClient { client =>
      return tags.map((t: String) => (t, client.smembers("tags:"+t).toList)).toMap
    }
  }

  def imagesForTag(tag: String) : List[String] = {
      allImages.getOrElse(tag, List())
  }

  def index = Action {
    val images = allImages.map(_._2).flatten.toList
    Ok(views.html.index(images))
  }

  def tag(tag: String) = Action {
    val images = imagesForTag(tag)
    Ok(views.html.tag(tag, images))
  }

  def random(tag: String) = Action {
      val imgs = imagesForTag(tag)
      val images = if(imgs.size > 0) List(Random.shuffle(imgs).head) else List()
      Ok(views.html.tag(tag, images))
  }

}
