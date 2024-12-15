# My Journey Companion - JEE Project - Docker version

## Docker launch

To start the application using docker, use this command line : 
```shell 
sudo docker compose up --build
```

Then, after the launch, you have to go to this url : http://localhost:8080

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
