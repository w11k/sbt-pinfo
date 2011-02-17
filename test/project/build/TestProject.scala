import com.weiglewilczek.sbtpinfo._
import sbt._

class TestProject(info: ProjectInfo) extends DefaultProject(info) with ProjectInfoPlugin {
  override val pinfoPackages =
    "com.weiglewilczek.sbtpinfo.test" :: "subpackage" :: Nil
}
