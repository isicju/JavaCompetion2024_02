rm -rf ../default/target/*
mvn -DjarName=example install  -f ../default/pom.xml
mvn -DjarName=example2 install  -f ../default/pom.xml
java -jar ../default/target/example-jar-with-dependencies.jar ../generator/data.txt
java -jar ../default/target/example2-jar-with-dependencies.jar ../generator/data.txt