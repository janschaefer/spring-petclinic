#!/bin/sh
# starts the petclinic at http://localhost:9967/petclinic
export MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=256m"
mvn antrun:run tomcat7:run -Ddemo=true
