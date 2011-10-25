name := "vraptor-social"

version := "0.1"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "jboss"     at "http://repository.jboss.org/nexus/content/groups/public/",
  "snapshots" at "http://scala-tools.org/repo-snapshots",
  "releases"  at "http://scala-tools.org/repo-releases",
  "javanet"   at "http://download.java.net/maven/2/",
  "snaps"     at "https://oss.sonatype.org/content/repositories/snapshots",
  "repo1"     at "http://repo1.maven.org/maven2/")

libraryDependencies ++= Seq(
  "br.com.caelum"            % "vraptor"               % "3.4.0"                   % "compile",
  "org.scribe"               % "scribe"                % "1.2.3"                   % "compile",
  "com.thoughtworks.xstream" % "xstream"               % "1.4.1"                   % "compile",
  "br.com.caelum.vraptor"    % "vraptor-environment"   % "1.0.1"                   % "compile",
  "javax.servlet"            % "javax.servlet-api"     % "3.0.1"                   % "compile",
  "org.scalatest"            %% "scalatest"            % "1.6.1"                   % "test",
  "org.mockito"              % "mockito-core"          % "1.8.5"           		   % "test"
)
