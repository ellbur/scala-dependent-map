import com.github.ellbur.collection.dependentmap.mutable.{Typed, DependentMutableMap}

object Main extends App {
  import ellbur.collection.dependentmap._

  trait Formatter extends Typed {
    type T
    def format(x: T): String
  }

  val stringFormatter = new Formatter {
    type T = String
    def format(x: String) = x
  }

  val intFormatter = new Formatter {
    type T = Int
    def format(x: Int): String = x.toString
  }

  val map = new DependentMutableMap[Formatter]

  map.put(stringFormatter)("Hello")
  map.put(stringFormatter)("Bob")
  map.put(intFormatter)(7)

  map foreach { p =>
    println(p.car.format(p.cdr))
  }
}
