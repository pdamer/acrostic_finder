package acrostic

import scala.collection.{immutable, mutable}

object AcrosticFinder {
  private val text_file = "test1.txt"
  private val dictionary_file = "dictionary.txt"
  private val dictionary: Dictionary

  def load_words: Array[Lookup] = {
    val word_list = new mutable.ArrayBuffer[Lookup]()
    
    for (word <- Source.fromFile(dictionary_file).getLines map { _.trim }) {
      val lu = new Lookup(word)
      lu.start
      word_list += lu
    }
    
    println("[!] generated " + wordlist.size + " dictionary words")
    return word_list
  }

  def main(args: Array[String]) {
    println("[!] starting acrostic finding")
    val dictionary = new Dictionary(load_words)
    dictionary.start()

    val column_splitter = new ColumnSplitter(text_file)
    column_splitter.start()

    System.exit(0)
  }
}


// vim: set ts=4 sw=4 et:
