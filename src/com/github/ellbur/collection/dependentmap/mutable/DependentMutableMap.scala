
package com.github.ellbur.collection.dependentmap.mutable

import scala.collection.mutable

trait Typed {
  type T
}

trait DependentPair[A<:Typed] {
  val car: A
  val cdr: car.T

  override def toString = s"D($car, $cdr)"
}

class DependentMutableMap[K<:Typed] extends mutable.Traversable[DependentPair[K]] {
  private[this] val map = mutable.Map[K,Any]()

  def put(key: K)(value: key.T) { map += ((key, value)) }
  def get(key: K): Option[key.T] = map.get(key) map (_.asInstanceOf[key.T])
  def getOrElseUpdate(key: K)(value: =>key.T): key.T = map.getOrElseUpdate(key, value).asInstanceOf[key.T]
  def foreach[U](f: DependentPair[K] => U) {
    map.foreach {
      case (k, v) => f(new DependentPair[K] {
        val car: K { type T = k.T } = k
        val cdr = v.asInstanceOf[k.T]
      })
    }
  }
}
