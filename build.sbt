name := "ScalaCourse"

version := "0.1"

scalaVersion := "3.3.1" // You can change to "2.13.12" if you want Scala 2

ThisBuild / javacOptions ++= Seq("-source", "17", "-target", "17")
ThisBuild / scalacOptions ++= Seq("-deprecation", "-feature")
