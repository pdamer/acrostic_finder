
package acrostic {
  import scala.actors._
  import scala.actors.Actor._
  
  class Dictionary(words: Array[Lookup]) extends Actor {
  
    def act() {
      loop {
        react {
          case c: Column => {
            for (word <- words)
            {
              word! c
            }
          }
          case x: Any   => println("Dictionary Error: Unknown message! " + x)
        }
      }
    }
  }
}
