package acrostic

import scala.io.Source
import scala.collection.{immutable, mutable}

object AcrosticFinder {
  private val text_file = "test1.txt"
  private val outputter = new Outputter
  
  def load_words(dictionary_file: String): Array[Lookup] = {
    val word_list = new mutable.ArrayBuffer[Lookup]()
    
    for (word <- Source.fromFile(dictionary_file).getLines map { _.trim }) {
      val lu = new Lookup(word, outputter)
      lu.start
      word_list += lu
    }
    
    println("[!] generated " + word_list.size + " dictionary words")
    return word_list.toArray
  }

  def main(args: Array[String]) {
    println("[!] starting acrostic finding")
    val dictionary = new Dictionary(load_words(args(0)))
    dictionary.start()
    outputter.start()
    

    val column_splitter = new ColumnSplitter(dictionary)
    column_splitter.start()
    column_splitter! args(1)
    Thread.sleep(1000)
    System.exit(0)
  }
}


// vim: set ts=4 sw=4 et:
