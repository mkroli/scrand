/*
 * Copyright 2016 Michael Krolikowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

name := "scrand"

organization := "com.github.mkroli"

crossScalaVersions := "2.13.0" :: "2.12.8" :: "2.11.12" :: "2.10.7" :: Nil

scalaVersion := crossScalaVersions.value.head

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation") ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, minor)) if minor >= 12 => Seq("-target:jvm-1.8")
  case _ => Seq("-target:jvm-1.6")
})

libraryDependencies ++= (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, 10)) => Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  case _ => Seq.empty
})

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

publishMavenStyle := true

Seq(Compile, Test).map { scope =>
  unmanagedSourceDirectories in scope += (sourceDirectory in scope).value / (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 13)) => "scala_2.13"
    case _ => "scala_2.12"
  })
}

releaseCrossBuild := true
