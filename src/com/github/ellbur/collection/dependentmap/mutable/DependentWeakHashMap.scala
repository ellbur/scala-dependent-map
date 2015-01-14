
package com.github.ellbur.collection.dependentmap.mutable

import ellbur.dependenttypes.Depender

import scala.collection.mutable

class DependentWeakHashMap[K <: Depender] {
  private[this] val map = new mutable.WeakHashMap[K, Any]

  def put(key: K)(value: key.T) { map += ((key, value)) }
  def get(key: K): Option[key.T] = map.get(key) map (_.asInstanceOf[key.T])
  def getOrElseUpdate(key: K)(value: =>key.T): key.T = map.getOrElseUpdate(key, value).asInstanceOf[key.T]
}
