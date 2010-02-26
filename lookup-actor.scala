
package acrostic {
  import scala.actors._
  import scala.actors.Actor._
  
  case class Match(word: String, column: Int, line: Int)

  class Lookup(word: String, outputter: Actor) extends Actor {
  
    def act() {
      loop {
        react {
          case c: Column => {
            for (found_word <- word.r.findAllIn(c.text).matchData) {
              outputter! new Match(word, c.index, found_word.start)
            }
          }
          case x: Any   => println("Lookup Error: Unknown message! " + x)
        }
      }
    }
  }
}