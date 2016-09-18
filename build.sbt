name := "testplatformv6"

version := "1.0"

lazy val `testplatformv6` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "Spring Snapshots" at "http://maven.springframework.org/snapshot"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs,
  "org.springframework" % "spring-context" % "4.3.2.RELEASE",
  "org.springframework.guice" % "spring-guice" % "1.0.0.BUILD-SNAPSHOT"
)

libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "1.1.0-M01"

libraryDependencies += "org.neo4j" % "neo4j-ogm-compiler" % "2.0.4"

libraryDependencies += "junit" % "junit" % "4.11"

libraryDependencies += "org.springframework.data" % "spring-data-neo4j" % "4.1.2.RELEASE"

libraryDependencies += "org.neo4j" % "neo4j-ogm-http-driver" % "2.0.0-M04"

libraryDependencies += "com.google.code.gson" % "gson" % "2.7"

routesGenerator := InjectedRoutesGenerator

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  