
package com.techsophy.repository

import com.techsophy.connection.{DBComponent, MySqlDBComponent}

import scala.concurrent.Future

trait StudentRepository extends StudentTable { this: DBComponent =>

  import driver.api._

  def create(student: Student): Future[Int] = db.run {
    studentTableAutoInc += student
  }

  def update(student: Student): Future[Int] = db.run {
    studentTableQuery.filter(_.id === student.id.get).update(student)
  }
  def getById(id: Int): Future[Option[Student]] = db.run {
    studentTableQuery.filter(_.id === id).result.headOption
  }

  def getAll(): Future[List[Student]] = db.run {
    studentTableQuery.to[List].result
  }
  def delete(id: Int): Future[Int] = db.run {
    studentTableQuery.filter(_.id === id).delete
  }

  def getStudentNameWithUniversityName():Future[List[(String,String)]] = {
    db.run
    {
      (for {
        (s, u) <- studentTableQuery join universityTableQuery on (_.UId === _.id)
      } yield (s.name, u.name)).to[List].result
    }
  }

  def getUniversityNameAndNoOfStudents():Future[List[(String,Int)]] = {

    val qu = (for {
      (s, u) <- studentTableQuery join universityTableQuery on (_.UId === _.id)
    } yield (s, u)).groupBy(_._2.name)

    val qu2 = qu.map {
      case (uid, css) => (uid, css.map(_._1.id).length)
    }

    db.run(qu2.to[List].result)
  }


}

private[repository] trait StudentTable extends UniversityTable{ this: DBComponent =>

  import driver.api._

  protected val studentTableQuery = TableQuery[StudentTable]

  protected def studentTableAutoInc = studentTableQuery returning studentTableQuery.map(_.id)

  private[StudentTable] class StudentTable(tag: Tag) extends Table[Student](tag, "student") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    val email = column[String]("email")
    val UId = column[Int]("university_id")
    def universityID = foreignKey("_fk", UId, universityTableQuery)(_.id)

    def * = (name,email,UId, id.?) <> (Student.tupled, Student.unapply)

  }

}

object StudentRepository extends StudentRepository with MySqlDBComponent

case class Student(name: String,email: String, UId:Int,id: Option[Int] = None)
