
package com.techsophy.repository

import com.techsophy.connection.H2DBComponent
import org.scalatest._
import funsuite.AnyFunSuite
import org.h2.jdbc.JdbcSQLException
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.util.{Failure, Success}



class UniversityRepositoryTest extends AnyFunSuite with UniversityRepository with H2DBComponent with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("Add new University") {
    val response = create(University("Pune University","Pune"))
    whenReady(response) { universityId =>
      assert(universityId === 4)
    }
  }


  test("Update  University info ") {
    val response = update(University("HCU","Gachibowli" ,Some(4)))
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Delete  university info  ") {
    val response = delete(2)
    whenReady(response.failed) { isUniversityDeleted=>
      intercept[JdbcSQLException]{
        throw isUniversityDeleted
      }
    }

  }

  test("Get university information list") {
    val bankInfo = getAll()
    whenReady(bankInfo) { result =>
      assert(result === List(University("HCU","Hyderabad",Some(1)), University("BHU","Banaras",Some(2)), University("DU","Delhi",Some(3))))
    }
  }

  test("Get university info by id  ") {
    val response = getById(1)
    whenReady(response) { res =>
      assert(res === Some(University("HCU","Hyderabad",Some(1))))
    }
  }




}
