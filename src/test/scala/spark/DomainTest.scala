package spark

import org.scalatest.funsuite.AnyFunSuite
import spark.Domain._

class DomainTest extends AnyFunSuite {

  test("Car case class should be constructed correctly") {
    val car = Car("Toyota", "Corolla", 2015, 15000)
    assert(car.make == "Toyota")
    assert(car.model == "Corolla")
    assert(car.year == 2015)
    assert(car.price == 15000)
    assert(car.currentPrice.isEmpty)
  }

  test("precioActual function should calculate the current price correctly") {
    val car = Car("Honda", "Civic", 2017, 20000)
    assert(precioActual(car) == 20000 * 0.85)
  }

  test("precioActual function should work on cars with different prices") {
    val carCheap = Car("Ford", "Fiesta", 2018, 10000)
    val carExpensive = Car("BMW", "X5", 2020, 50000)

    assert(precioActual(carCheap) == 10000 * 0.85)
    assert(precioActual(carExpensive) == 50000 * 0.85)
  }
  test("extreme prices should be handled correctly") {
    val carMax = Car("Ferrari", "LaFerrari", 2021, Double.MaxValue)
    val carMin = Car("Trabant", "601", 1980, Double.MinValue)

    assert(precioActual(carMax) == Double.MaxValue * 0.85)
    assert(precioActual(carMin) == Double.MinValue * 0.85)
  }

  test("price 0 should be handled correctly") {
    val car = Car("Renault", "Clio", 2019, 0)
    assert(precioActual(car) == 0)
  }
}
