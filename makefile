all:
	mvn package
	java -cp target\DETestProject-1.0-SNAPSHOT.jar idv.rozen.App


package:
	mvn package