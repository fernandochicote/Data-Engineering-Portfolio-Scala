//Un case class en Scala sirve para definir de manera concisa
// y fácilmente desestructurable una clase de datos inmutables

object CaseClassExample {

  // Clase para direcciones
  case class Direccion(calle: String, ciudad: String, cp: String)

  // Clase para personas
  case class Persona(nombre: String, edad: Int, direccion: Direccion)

  def main(args: Array[String]): Unit = {
    // Crear instancias
    val dir1 = Direccion("C/ Huesca 89", "Logroño", "26049")
    val dir2 = Direccion("C/ Bravo Murillo 23", "Madrid", "28015")

    val p1 = Persona("Fernando", 29, dir1)
    val p2 = Persona("Mario", 52, dir2)

    // Imprimir instancias
    println(s"Persona 1: $p1")
    println(s"Persona 2: $p2")

    // Campos de las instancias
    println(s"Nombre de persona 1: ${p1.nombre}")
    println(s"Ciudad de persona 2: ${p2.direccion.ciudad}")

    // Comparar instancias
    val p3 = Persona("Rafa", 40, dir2)
    println(s"p1 == p3: ${p1 == p3}")

    // Copiar instancia
    val p4 = p1.copy(edad = 96)
    println(s"Persona 4 (copia de persona 1 con edad cambiada): $p4")

    // Descomposición mediante patrones
    val Persona(nombre, edad, Direccion(calle, ciudad, cp)) = p2
    println(s"Descomposición de persona 2: nombre = $nombre, edad = $edad, calle = $calle, ciudad = $ciudad, cp = $cp")
  }
}
