Team:
Martin Conlon 16403502
Michael Bradley 16407672

Setup:
to run first create a local mysql schema called testdb,
In src/resources/application.properites change spring.datasource.password to be equal to your mysql server's password,
go into the mvn folder and run spring-boot:run 
After the applicaiton is first run a entry in the roles table of testdb MUST be created with id=1 and name=ROLE_USER
then open the index.html page with live server
