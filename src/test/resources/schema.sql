CREATE TABLE university(id int PRIMARY KEY auto_increment,name varchar(200),location varchar(200));

CREATE TABLE student(id int PRIMARY KEY auto_increment,name varchar(200),email varchar(200), university_id int references university(id) );


