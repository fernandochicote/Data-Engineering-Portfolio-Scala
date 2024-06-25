// Un trait en Scala sirve para definir comportamientos
// y características que pueden ser compartidos por múltiples clases.

// Definición de un trait que define un metodo de saludar
trait Saludo {
  def saludar(nombre:String): Unit = println(s"Hola, ¿Cómo estás $nombre?")
}

// Clase que extiende el trait Saludo
class Persona extends Saludo

object TraitsExample {
  def main(args: Array[String]): Unit = {
    val p1 = new Persona()
    p1.saludar("Fernando")
  }
}
