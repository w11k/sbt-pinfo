import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val sbtPinfo = "com.weiglewilczek.sbt-pinfo" % "sbt-pinfo" % "0.2-SNAPSHOT"
}
