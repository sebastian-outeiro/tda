if ! [ -f target/*.jar ]; then
    mvn compile
	mvn package
fi
java -jar target/*.jar $1