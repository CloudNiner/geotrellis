/*
 * Copyright 2019 Azavea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geotrellis.raster.summary.polygonal

/** Visitor for cell values of T that should record their state of R
  *
  * The user should implement concrete subclasses that update the value of `result` as
  * necessary on each call to `visit(raster: T, col: Int, row: Int)`
  */
trait CellVisitor[-T, R] {

  // This falls into the initialization trap with Java null pointer exceptions.
  // If a user creates a CellVisitor where empty is an initialized object and uses val, e.g.
  // val empty = Array[Int](), a null pointer exception will be thrown.
  // We have to use var here because we're updating the value in visit method calls.
  // TODO: Can we defensively prevent the user from hitting this issue?
  // TODO: Should we explore an alternate implementation that allows the user to explicitly
  //       override the default value of result? Currently this can be done by overriding
  //       empty instead.
  var result: R = empty

  /**
    * Override to set the initial value of `result` for this CellVisitor.
    */
  def empty: R

  def visit(raster: T, col: Int, row: Int): Unit
}

object CellVisitor {
  def apply[T, R](implicit ev: CellVisitor[T, R]): CellVisitor[T, R] = ev
}
