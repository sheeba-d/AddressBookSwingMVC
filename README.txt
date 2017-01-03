To run the project follow the instructions given below:

1.Create a database 'addressbook' in MySQL database and import the 'addressbook.sql' file.
		
		(OR)

  Create a table 'addbook' with the following structure in MySQL database

	fieldname	type
	
	name		varchar2(20)
	mob		varchar2(15)
	email		varchar2(50)


2.Open the project in Netbeans IDE.

3.Resolve the jar file issues if any.(mysql-connector-java.jar)

4.Change the connection string values(url,username,password) according to your database configuration in \src\mvc\swing\dao\DbConnect.java file  if needed.

5.Always make sure that database server is started before executing the project.