import Dependencies._

// A good example of build.sbt https://github.com/lift/framework/blob/master/build.sbt
inThisBuild(
  Seq(
    organization := "org.asarkar",
    version := "1.0.0",
    scalaVersion := "2.12.7",
    scalacOptions ++= Seq(
      "-unchecked",
      "-feature",
      //  "-language:existentials",
      //  "-language:higherKinds",
      "-language:implicitConversions",
      //  "-language:postfixOps",
      "-deprecation",
      "-encoding",
      "utf8"
    ),
    libraryDependencies ++= allDeps
  )
    ++ inConfig(Test)(Seq(
    testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-o", "-e"),
    logBuffered := false,
    fork := true,
    parallelExecution := false,
    javaOptions ++= Seq("-ea")
  ))
)

// http://www.wartremover.org/doc/warts.html
Compile / wartremoverWarnings ++= Warts.unsafe
  .filterNot(Set(Wart.NonUnitStatements).contains)

lazy val `algorithms-design-analysis-2` = (project in file("."))
  .aggregate(
    `test-util`, `homework-1`
  )

lazy val `test-util` = project
lazy val `data-structures` = project

lazy val `homework-1` = project
  .dependsOn(`test-util` % Test, `data-structures`)
