# My Journey Companion - JEE Project - Docker version

## Docker launch

You have to write down your MySQL Username and MySQL Password in the docker-compose on the MYSQL_ROOT_PASSWORD,
MYSQL_USER, MYSQL_PASSWORD, DATABASE_USERNAME and DATABASE_PASSWORD.

To start the application using docker, use this command line :

```shell 
docker compose up --build -d
```

If there is a problem, try to execute this command :

```shell
docker compose restart app
```

Then, after the launch, you have to go to this url : http://localhost:8080


If you want to try to launch the application without docker, change on application.properties this line :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_my_journey_companion
```
If you want to use docker, check if you have the correct line : 
```properties
spring.datasource.url=jdbc:mysql://db:3306/db_my_journey_companion
```


-------

## Initial README

### Quick Start

1. Create the database in your MySQL server :

```shell
mysql -u username -p
create database db_my_journey_companion;
```

2. Build the application :

```shell
.gradlew build
```

3. Run the application :

```shell
DATABASE_USERNAME=yourMySQLUsername DATABASE_PASSWORD=yourMySQLPassword ./gradlew bootRun
```

4. Access the application :

You can access the application at http://localhost:8080

## Configuration

The project uses Java 21

### Create the database

You have to connect to MySQL and create the database.

First, access the MySQL console :

```bash
mysql -u username -p
```

In the MySQL console, create the database :

```mysql
create database db_my_journey_companion;
```

### Set up your variables environment

You have two variables to set :

- DATABASE_USERNAME=yourMySqlUsername
- DATABASE_PASSWORD=yourMySqlPassword

### Run the application with your favourite IDE

### Login to the website

Log on to http://localhost:8080 on a browser
