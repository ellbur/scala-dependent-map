
package com.github.ellbur.collection.dependentmap.immutable

import ellbur.dependenttypes._

trait DependentMap[Key <: Depender] extends Iterable[DependentPair[Key]] {
  def get(key: Key): Option[key.T]
}

object DependentMap {
  def apply[Key <: Depender](elems: DependentPair[Key]*): DependentMap[Key] =
    new DefaultDependentMap[Key](Map(elems map (_.toAnyPair): _*))
}

class DefaultDependentMap[Key <: Depender](underlying: Map[Key,Any]) extends DependentMap[Key] {
  def get(key: Key): Option[key.T] = underlying.get(key).asInstanceOf[Option[key.T]]

  def iterator = underlying.iterator map {
    case (key, value) =>
      new DependentPair[Key] {
        val car: Key { type T = key.T } = key
        val cdr = value.asInstanceOf[key.T]
      }
  }
}
