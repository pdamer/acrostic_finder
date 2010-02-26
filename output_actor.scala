
package acrostic {
  import scala.actors._
  import scala.actors.Actor._

  class Outputter extends Actor {
    
    def act() {
      loop {
        react {
          case s: String => {
            println(s)
          }
          case m: Match => {
            println("Found match for '" + m.word + "' at column: " + m.column + ", line " + m.line)
          }
          case x: Any   => println("Error: Unknown message! " + x)
        }
      }
    }
  }
}


// vim: set ts=4 sw=4 et:
