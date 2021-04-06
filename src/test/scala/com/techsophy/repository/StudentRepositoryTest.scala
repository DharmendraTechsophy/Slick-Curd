package com.techsophy.repository

import com.techsophy.connection.H2DBComponent
import org.scalatest._
import funsuite.AnyFunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}



class StudentRepositoryTest extends AnyFunSuite with StudentRepository with H2DBComponent with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("add Student") {
    val response = create(Student("Nidhi","n@gmail.com",1))
    whenReady(response) { universityId =>
      assert(universityId === 6)
    }
  }

  test("Update  Student info ") {
    val response = update(Student("Nidhi Raj","nraj@gmail.com",1,Some(1)))
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Delete Student info  ") {
    val response = delete(2)
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Get Student information list") {
    val bankInfo = getAll()
    whenReady(bankInfo) { result =>
      assert(result === List(Student("Himanshu","him@gmail.com",3,Some(1)), Student("Sorya","sor@gmail.com",2,Some(2)), Student("Aditya","adi@gmail.com",1,Some(3)), Student("Dharmendra","dhar@gmail.com",1,Some(4)), Student("Vinit","vinit@gmail.com",1,Some(5))))
    }
  }

  test("Get Student info by id  ") {
    val response = getById(1)
    whenReady(response) { res =>
      assert(res === Some(Student("Himanshu","him@gmail.com",3,Some(1))))
    }
  }

  test("Get Student Name and University Name  ") {
    val response = getStudentNameWithUniversityName()
    whenReady(response) { res =>
      assert(res === List(("Himanshu","DU"), ("Sorya","BHU"), ("Aditya","HCU"), ("Dharmendra","HCU"), ("Vinit","HCU")))
    }
  }


  test("Get University Name and No of Students  ") {
    val response = getUniversityNameAndNoOfStudents()
    whenReady(response) { res =>
      assert(res === List(("DU",1), ("HCU",3), ("BHU",1)))
    }
  }




}
