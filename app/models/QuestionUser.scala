package models

case class QuestionUser(val id: Long, val question: String, opt1: String, opt2: String, opt3: String, opt4: String,
                        note: Option[String])