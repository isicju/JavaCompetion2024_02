mvn clean -DjarName=example install  -f ../default/pom.xml
java -jar default/target/example-jar-with-dependencies.jar generator/data.txt