
package acrostic {
  import scala.actors._
  import scala.actors.Actor._
  import scala.io.Source

  case class Column(text: String, index: Int)

  class ColumnSplitter( val dictionary: Actor) extends Actor {
    
    def act() {
      loop {
        receive {
          case s: String => {
            for (i <- 0 until 5) {
              var column = Source.fromFile(s).getLines map {(line: String) => letter_at(line.trim,i)  } mkString ""
              //println("created column" + column)
              dictionary! new Column(column, i)
            }
          }
          
          case x: Any   => println("Error: Unknown message! " + x)
        }
      }
    }
    
    def letter_at(s: String, i: Int): String = {
      try {
        return s.substring(i,i+1)
      }
      catch {
        case e: java.lang.StringIndexOutOfBoundsException => ""
      }
    }
  }
}


// vim: set ts=4 sw=4 et:
