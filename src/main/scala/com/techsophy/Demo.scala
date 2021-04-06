package com.techsophy


import com.techsophy.repository._

import scala.concurrent.ExecutionContext.Implicits.global

import scala.util._



//CREATE TABLE university(id int PRIMARY KEY auto_increment,name varchar(200),location varchar(200));

//CREATE TABLE student(id int PRIMARY KEY auto_increment,name varchar(200),email varchar(200), university_id int references university(id) );



object Demo extends App {

   //create

//  val universityIDs = UniversityRepository.create(University("HCU","hyderabad"))
//
//  universityIDs.onComplete {
//    case Success(id) =>
//      StudentRepository.create(Student("Dharmendra","d@gmail.com",1))
//      StudentRepository.create(Student("Vinit","v@gmail.com",1))
//      UniversityRepository.create(University("BHU","Banaras"))
//      UniversityRepository.create(University("DU","Delhi"))
//    case _ => println("Error Found in Demo File...........")
//  }
//
//  StudentRepository.create(Student("Aditya","a@gmail.com",1))
//  StudentRepository.create(Student("Sorya","s@gmail.com",2))
//  StudentRepository.create(Student("harshit","h@gmail.com",3))


    //join1  : student name || university name
  //  join2  :  university name || no of students

//  StudentRepository.getStudentNameWithUniversityName().foreach(println)
//  StudentRepository.getUniversityNameAndNoOfStudents().foreach(println)

  //getAll students
  //StudentRepository.getAll().foreach(println)

  //getStudent by id
  //StudentRepository.getById(2).foreach(x=>println(x))


  //update
  //StudentRepository.update(Student("himanshu","him@gmail.com",3,Some(1))).foreach(println)

  Thread.sleep(10 * 1000)

}