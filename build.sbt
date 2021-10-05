import Dependencies._
import com.typesafe.sbt.SbtNativePackager.autoImport.maintainer
import org.irundaia.sbt.sass._
import sbt.Keys._
import sbt._

lazy val scala212v              = "2.12.8"
lazy val scala213v              = "2.13.4"
lazy val reactJsV               = "17.0.2"
lazy val scalaJsReactV          = "1.7.7"
lazy val supportedScalaVersions = List(scala212v, scala213v)

scalacOptions ++= CompilerOptions.cOptions

lazy val projectSettings = Seq(
  version            := "1.0",
  crossScalaVersions := supportedScalaVersions,
  scalaVersion       := scala213v,
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full))

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("common"))
  .settings(projectSettings: _*)
  .settings(libraryDependencies ++= commonDependencies)

lazy val `web-server` = (project in file("web-server"))
  .settings(projectSettings: _*)
  .settings(
    maintainer              := "scala.js",
    scalaJSProjects         := Seq(`scalajs-client`),
    Assets / pipelineStages := Seq(scalaJSPipeline),
    pipelineStages          := Seq(digest, gzip),
    Compile / compile       := ((Compile / compile) dependsOn scalaJSPipeline).value)
  .settings(
    scalacOptions ++= CompilerOptions.cOptions,
    SassKeys.cssStyle                      := Maxified,
    SassKeys.generateSourceMaps            := false,
    SassKeys.syntaxDetection               := ForceScss,
    Assets / LessKeys.less / includeFilter := "*.less",
    Assets / LessKeys.less / excludeFilter := "_*.less",
    Global / onChangedBuildSource          := ReloadOnSourceChanges,
    run / fork                             := true,
    resolvers ++= Seq(
      "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
      "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/",
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")),
    libraryDependencies ++= rootDependencies ++ Seq(jdbc, ehcache, ws, specs2 % Test, guice))
  .enablePlugins(PlayScala, WebScalaJSBundlerPlugin)
  .dependsOn(common.jvm)

lazy val `scalajs-client` = (project in file("scalajs-client"))
  .settings(projectSettings: _*)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Seq(
      "org.scala-js"                      %%% "scalajs-dom"     % "1.2.0",
      "io.github.cquiroz"                 %%% "scala-java-time" % "2.3.0",
      "com.github.japgolly.scalajs-react" %%% "core"            % scalaJsReactV,
      "com.github.japgolly.scalajs-react" %%% "extra"           % scalaJsReactV),
    Compile / npmDependencies ++= Seq("react" -> reactJsV, "react-dom" -> reactJsV))
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb, ScalaJSBundlerPlugin)
  .dependsOn(common.js)

lazy val `scalajs_react` = (project in file("."))
  .aggregate(`web-server`, `scalajs-client`, common.js, common.jvm)

Global / onLoad := (Global / onLoad).value.andThen(state => "project web-server" :: state)