package controllers

import javax.inject.{Inject, Singleton}

import models.{QuestionRepository, QuestionSystem, QuestionUser}
import play.api.mvc._
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future

@Singleton
class ExamController @Inject()(repo: QuestionRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  var question: List[QuestionSystem] = List()

  def refresh = repo.list().onComplete({
    case Success(listInt) => question = listInt.toList
    case Failure(exception) => println(exception)
  })

  var count = 0

  def index() = Action { implicit request =>
    refresh
    val q = QuestionSystem.toQuestionUser(question(count))
    Ok(views.html.first(q, this))
  }

  def nextQuestion() = Action { implicit request =>
    count += 1
    val q = QuestionSystem.toQuestionUser(question(count))
    Ok(views.html.first(q, this))
  }

  def getStatus() = Action { implicit request =>
    Ok("In progress")
  }
}
