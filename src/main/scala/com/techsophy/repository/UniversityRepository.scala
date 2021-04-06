package com.techsophy.repository

import com.techsophy.connection.{DBComponent, MySqlDBComponent}

import scala.concurrent.Future

trait UniversityRepository extends UniversityTable { this: DBComponent =>

  import driver.api._

  def create(university: University): Future[Int] = db.run {
    universityTableAutoInc += university
  }

  def update(university: University): Future[Int] = db.run {
    universityTableQuery.filter(_.id === university.id.get).update(university)
  }

  def getById(id: Int): Future[Option[University]] = db.run {
    universityTableQuery.filter(_.id === id).result.headOption
  }

  def getAll(): Future[List[University]] = db.run {
    universityTableQuery.to[List].result
  }
  def delete(id: Int): Future[Int] = db.run {
    universityTableQuery.filter(_.id === id).delete
  }



}

private[repository] trait UniversityTable { this: DBComponent =>

  import driver.api._

  protected val universityTableQuery = TableQuery[UniversityTable]

  protected def universityTableAutoInc = universityTableQuery returning universityTableQuery.map(_.id)

  private[UniversityTable] class UniversityTable(tag: Tag) extends Table[University](tag, "university") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    val location = column[String]("location")

    def * = (name,location, id.?) <> (University.tupled, University.unapply)

  }

}

object UniversityRepository extends UniversityRepository with MySqlDBComponent

case class University(name: String,location:String, id: Option[Int] = None)
