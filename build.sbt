ThisBuild / version := "0.0.1"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "simple-graalvm-native-image-issue-demo"
  )
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8", // no effects to the issue
      "-feature",
      "-unchecked",
    ),
    GraalVMNativeImage / mainClass := Some("simple.main"),
    graalVMNativeImageOptions ++= Seq(
      // "--verbose",
      "--silent",
      // "--static",
      // "-H:+StaticExecutableWithDynamicLibC",
      "-H:+UnlockExperimentalVMOptions",
      "--no-fallback",
      // "-march=native",
      "-J-Dfile.encoding=UTF8",
      "-J-Dsun.stdout.encoding=UTF-8"
    )
  )

addCommandAlias("native", "graalvm-native-image:packageBin")
