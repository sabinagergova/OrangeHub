Instructions:

#1. How to build the project with Maven
Go to the project directory, where the pom.xml is stored, and run the following command:
mvn clean install
This will build the project.
#2. Run the application
Go to the target/ directory and there find the .jar file of the desired code snapshot you wish to run.
Then run the following line:
java -cp your.build.snapshot.jar package.path.to.main.class
#3. Usage
When application run, write 
curl localhost:5661
in command line, and you will see list of action you can use. When use login, write -v in the end of the line to see our cookie.