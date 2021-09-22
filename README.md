# WebInMemoryDB

WebInMemoryDB is a web application interface for my [InMemoryDB](https://github.com/Malek99/InMemoryDB) project. I used Spring framework to provide a web interface to that CLI project to make it easy and convenient to manipulate the database.

# Dependencies
All of the dependencies of this project are handled by maven. However, you have to manually install the InMemoryDB libraries. To do this clone the project:
```
git clone https://github.com/Malek99/InMemoryDB.git
```

And then run:

```
cd InMemoryDB
mvn clean install
```

# Requirements
This project uses jdbcAuthentication. So, users credentials are stored on a database. I used a MySQL database in this project but you can change this in the properties file under `src/main/resources/application.properties`. I provided a `data.sql` file so you can import the tables used by this project into a database of your choice. 

After importing the tables you will have three users: an Admin with the username and password `admin`, a Writer with the username and password `witer`, and a Reader with the username and password `reader`. You can manage users by first logging in to the application using the admin credentials. You can also change the password of any user by visiting the `Account` tab in the application.

# Build

To build this project and generate the java war file run the following maven command:
```
mvn clean package
```

After this, you will find the war file under `target/database.war`. You can then deploy this war file to a tomcat server.



# Run

You can also run this project on computer directly without having to deploy it on a tomcat server using spring-boot maven plugin. You run:
```
mvn spring-boot:run
```
And the server will run and listen to port 8081. So you can open the web application in any browser by visiting [http://localhost:8081](http://localhost:8081)
