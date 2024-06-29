package ejemplos_basicos

// En Scala, puedes usar un alias de tipo (type alias) para dar un nombre más legible a un tipo existente.
// Esto puede mejorar la claridad del código y hacer que los tipos complejos sean más fáciles de manejar

object TypeAliasExample {
  // Definicion del alias de tipo
  type StringList = List[String]

  def main(args: Array[String]): Unit = {

    val names: StringList = List("Alice", "Bob", "Charlie")

    println(names)

    def printNames(names: StringList): Unit = names.foreach(println)

    printNames(names)
  }
}
