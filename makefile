all:
	
	mvn exec:java -Dexec.mainClass="idv.rozen.App" 
clean:
	mvn clean
package:
	mvn package

test:
	mvn clean compile package
	java -jar target\DETestProject-1.0-SNAPSHOT.jar