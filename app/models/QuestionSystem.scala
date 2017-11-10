package models

case class QuestionSystem(id: Long, question: String,
                          opt1: String, opt2: String,
                          opt3: String, opt4: String,
                          answer: Int,
                          note: Option[String]) {
  def apply(q: QuestionSystem): QuestionSystem =
    new QuestionSystem(q.id, q.question, q.opt1, q.opt2, q.opt3, q.opt4, q.answer, q.note)
}

object QuestionSystem {
   def toQuestionUser(questionSystem: QuestionSystem): QuestionUser =
    QuestionUser(questionSystem.id, questionSystem.question, questionSystem.opt1, questionSystem.opt2, questionSystem.opt3,
      questionSystem.opt4, questionSystem.note)
}
