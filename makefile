all:
	mvn clean compile package
	java -jar target\DETestProject-1.0-SNAPSHOT.jar
clean:
	mvn clean
package:
	mvn package