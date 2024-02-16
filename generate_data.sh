mvn clean -DjarName=dmitrii -Dname=Example install  -f ../default/pom.xml
java -jar default/target/dmitrii-jar-with-dependencies.jar generator/data.txt