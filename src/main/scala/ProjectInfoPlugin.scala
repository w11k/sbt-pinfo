/*
 * Copyright 2011 Weigle Wilczek GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weiglewilczek.sbtpinfo

import java.io.PrintWriter
import sbt._
import java.lang.String

trait ProjectInfoPlugin extends MavenStyleScalaPaths {

  final lazy val pinfo = pinfoAction

  protected def pinfoPackages: List[String] =
    Nil

  protected def pinfoObjectName: String =
    "ProjectInfo"

  protected def pinfoProjectVersion: String =
    projectVersion.value.toString

  protected def pinfoFile: Path =
    mainScalaSourcePath / (pinfoObjectName + ".scala")

  protected def pinfoAction =
    task {
      val contents =
        (pinfoPackages map { "package " + _ }) :::
        ("object %s {" format pinfoObjectName) ::
        ("  val version: String = \"%s\"" format pinfoProjectVersion) ::
        "}" ::
        Nil
      val out = new PrintWriter(pinfoFile.asFile)
      try {
        val separator = System.getProperty("line.separator", "\\n")
        out.println(contents mkString separator)
        None
      } catch {
        case e: Exception => Some(e.toString)
      } finally {
        try { out.close() } catch { case e: Exception => }
      }
    } describedAs "Creates a ProjectInfo object."
}
