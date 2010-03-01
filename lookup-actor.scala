
package acrostic {
  import scala.actors._
  import scala.actors.Actor._
  import scala.util.matching.Regex

  case class Match(word: String, column: Int, line: Int)

  class Lookup(word_reg: Regex, outputter: Actor) extends Actor {
    
    def this(word: String, outputter: Actor) = 
      this(word.r, outputter)


    def act() {
      loop {
        react {
          case c: Column => {
            for (found_word <- word_reg.findAllIn(c.text).matchData) {
              outputter! new Match(found_word.toString, c.index, found_word.start)
            }
          }
          case x: Any   => println("Lookup Error: Unknown message! " + x)
        }
      }
    }
  }
}
