package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class QuestionRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class QuestionTable(tag: Tag) extends Table[QuestionSystem](tag, "questions") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def question = column[String]("question")
    def opt1 = column[String]("opt1")
    def opt2 = column[String]("opt2")
    def opt3 = column[String]("opt3")
    def opt4 = column[String]("opt4")
    def answer = column[Int]("answer")
    def note = column[Option[String]]("note")
    def * = (id, question, opt1, opt2, opt3, opt4, answer, note) <> ((QuestionSystem.apply _).tupled, QuestionSystem.unapply)
  }

  private val questions = TableQuery[QuestionTable]

  def insert(id: Long, question: String, opt1: String, opt2: String, opt3: String, opt4: String,
             answer: Int, note: Option[String]): Future[QuestionSystem] = db.run {
    (questions.map(q => (q.question, q.opt1, q.opt2, q.opt3, q.opt4, q.answer, q.note))
      returning questions.map(_.id)
      into ((tuple, id) => QuestionSystem(id, tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7))
      ) += (question, opt1, opt2, opt3, opt4, answer, note)
  }

  def list(): Future[Seq[QuestionSystem]] = db.run {
    questions.result
  }

  def find(id: Long): Future[Seq[QuestionSystem]] = db.run {
    questions.filter(_.id === id).result
  }
}
