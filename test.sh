export MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=256m"  # is necessary for the integration tests
mvn -Dmaven.test.failure.ignore=true verify
