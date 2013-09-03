import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "tarhun"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.typesafe" %% "play-plugins-redis" % "2.1.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "org.sedis" at "http://pk11-scratch.googlecode.com/svn/trunk"
  )

}
