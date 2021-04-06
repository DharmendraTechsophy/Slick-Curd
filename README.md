# Slick-curd-operations

#Run unit test:

$ sbt test

Unit tests have used h2 database.If you want run demo app so you need to create database in MySQL.

#Follow these steps:


Step 1 : login into mysql server then perform following task

mysql > create database mydb;

mysql > use mydb;

mysql> CREATE TABLE university(id int PRIMARY KEY auto_increment,name varchar(200),location varchar(200));

mysql> CREATE TABLE student(id int PRIMARY KEY auto_increment,name varchar(200),email varchar(200), university_id int references university(id) );



Step 2 : for Running the App


$ sbt run


