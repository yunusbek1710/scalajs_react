import play.sbt.PlayImport.guice
import sbt._
object Dependencies {

  object Versions {
    val cats             = "2.1.1"
    val CirceVersion     = "0.13.0"
    val fs2              = "2.4.0"
    val akka             = "2.6.14"
    val doobie           = "0.9.4"
    val scalaLogging     = "3.9.2"
    val postgreSql       = "42.2.18"
    val scalaTestPlus    = "5.1.0"
    val playMailer       = "8.0.1"
    val playWebjars      = "2.8.0-1"
    val jquery           = "3.5.1"
    val jQueryUI         = "1.11.4"
    val jQueryFileUpload = "9.10.1"
    val jQueryBlockUI    = "2.70"
    val bootstrap        = "4.5.0"
    val datepicker1      = "1.7.1"
    val datePicker       = "2.4.4"
    val koMapping        = "2.4.1"
    val knockout         = "3.3.0"
    val toastr           = "2.1.2"
    val fontAwesome      = "5.14.0"
    val momentJs         = "2.8.1"
    val popperJs         = "1.12.9"
    val logBack          = "1.3.0-alpha5"
    val logOver          = "1.7.30"
    val openTable        = "0.10.0"
    val jsonJoda         = "2.9.0"
    val jQueryMask       = "1.14.12"
    val pureConfig       = "0.13.0"
    val compass          = "0.12.7"
    val dateTimePicker   = "2.4.4"
    val pagination       = "1.4.1"
    val scss             = "0.12.7"
    val bot4s            = "1.0.7"
  }

  object Libraries {
    val cats            = "org.typelevel"              %% "cats-core"            % Versions.cats
    val circe           = "io.circe"                   %% "circe-core"           % "0.13.0"
    val catsEffect      = "org.typelevel"              %% "cats-effect"        % "2.1.4"
    val fs2Core         = "co.fs2"                     %% "fs2-core"             % Versions.fs2
    val fs2IO           = "co.fs2"                     %% "fs2-io"               % Versions.fs2
    val fs2Reactive     = "co.fs2"                     %% "fs2-reactive-streams" % Versions.fs2
    val fs2Experimental = "co.fs2"                     %% "fs2-experimental"     % Versions.fs2
    val scalaLogging    = "com.typesafe.scala-logging" %% "scala-logging"        % Versions.scalaLogging
    val scalaTestPlus   = "org.scalatestplus.play"     %% "scalatestplus-play"   % Versions.scalaTestPlus % Test
    val jsonJoda        = "com.typesafe.play"          %% "play-json-joda"       % Versions.jsonJoda
    val pureConfig      = Seq("com.github.pureconfig"  %% "pureconfig" % Versions.pureConfig)
    val ioNetty         = "io.netty"                    % "netty"                % "3.10.6.Final"
    val bot4s           = "uz.scala"                   %% "bot4scala"            % Versions.bot4s

    val playMailerLibs         = Seq(
      "com.typesafe.play" %% "play-mailer"       % Versions.playMailer,
      "com.typesafe.play" %% "play-mailer-guice" % Versions.playMailer)
    val circeLibs              = Seq(
      "io.circe" %% "circe-core"           % Versions.CirceVersion,
      "io.circe" %% "circe-generic"        % Versions.CirceVersion,
      "io.circe" %% "circe-literal"        % Versions.CirceVersion,
      "io.circe" %% "circe-generic-extras" % Versions.CirceVersion,
      "io.circe" %% "circe-parser"         % Versions.CirceVersion)
    val logLibs                = Seq(
      "org.slf4j"                   % "log4j-over-slf4j" % Versions.logOver,
      "com.typesafe.scala-logging" %% "scala-logging"    % Versions.scalaLogging,
      "ch.qos.logback"              % "logback-core"     % Versions.logBack,
      "ch.qos.logback"              % "logback-classic"  % Versions.logBack % Test)
    val dbLibs                 = Seq(
      "com.opentable.components" % "otj-pg-embedded" % Versions.openTable % Test,
      "org.postgresql"           % "postgresql"      % Versions.postgreSql)
    val akka                   = Seq(
      "com.typesafe.akka" %% "akka-actor"                 % Versions.akka,
      "com.typesafe.akka" %% "akka-remote"                % Versions.akka,
      "com.typesafe.akka" %% "akka-persistence"           % Versions.akka,
      "com.typesafe.akka" %% "akka-serialization-jackson" % Versions.akka,
      "com.typesafe.akka" %% "akka-testkit"               % Versions.akka % Test)
    val doobieLibs             = Seq(
      "org.tpolecat" %% "doobie-core"      % Versions.doobie,
      "org.tpolecat" %% "doobie-postgres"  % Versions.doobie, // Postgres driver 42.2.12 + type mappings.
      "org.tpolecat" %% "doobie-specs2"    % Versions.doobie % Test, // Specs2 support for typechecking statements.
      "org.tpolecat" %% "doobie-scalatest" % Versions.doobie % Test // ScalaTest support for typechecking statements.
    )
    val webjarsLibs            = Seq(
      "org.webjars"      %% "webjars-play"             % Versions.playWebjars,
      "org.webjars"       % "jquery"                   % Versions.jquery,
      "org.webjars"       % "bootstrap"                % Versions.bootstrap,
      "org.webjars"       % "bootstrap-select"         % "1.13.18",
      "org.webjars"       % "bootstrap-datepicker"     % Versions.datepicker1,
      "org.webjars"       % "bootstrap-datetimepicker" % Versions.datePicker,
      "org.webjars.bower" % "knockout-mapping"         % Versions.koMapping,
      "org.webjars.bower" % "compass-mixins"           % Versions.compass,
      "org.webjars"       % "knockout"                 % Versions.knockout,
      "org.webjars"       % "toastr"                   % Versions.toastr,
      "org.webjars"       % "font-awesome"             % Versions.fontAwesome,
      "org.webjars"       % "momentjs"                 % Versions.momentJs,
      "org.webjars"       % "popper.js"                % Versions.popperJs,
      "org.webjars"       % "jquery-ui-src"            % Versions.jQueryUI,
      "org.webjars"       % "jquery-file-upload"       % Versions.jQueryFileUpload,
      "org.webjars.bower" % "jquery-mask-plugin"       % Versions.jQueryMask,
      "org.webjars"       % "jquery-blockui"           % Versions.jQueryBlockUI,
      "org.webjars.bower" % "compass-mixins"           % Versions.scss,
      "org.webjars.bower" % "twbs-pagination"          % Versions.pagination exclude ("org.webjars.bower", "jquery"))

    val pdfLibs: Seq[ModuleID] = Seq(
      "commons-io"              % "commons-io"         % "2.6",
      "net.sf.jtidy"            % "jtidy"              % "r938",
      "org.xhtmlrenderer"       % "flying-saucer-core" % "9.1.22",
      "org.xhtmlrenderer"       % "flying-saucer-pdf"  % "9.1.22",
      "nu.validator.htmlparser" % "htmlparser"         % "1.4")
  }
  val commonDependencies: Seq[ModuleID] =
    Seq(
      Libraries.cats,
      Libraries.jsonJoda,
      Libraries.catsEffect,
      guice) ++ Libraries.circeLibs ++ Libraries.playMailerLibs ++ Libraries.pureConfig

  val rootDependencies: Seq[ModuleID] = commonDependencies ++
    Seq(Libraries.cats, Libraries.scalaLogging, Libraries.scalaTestPlus, Libraries.ioNetty, Libraries.bot4s) ++
    Libraries.dbLibs ++
    Libraries.akka ++
    Libraries.doobieLibs ++
    Libraries.webjarsLibs ++
    Libraries.pdfLibs
}
