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
  // TODO: Change to Option[R] -- Awkward. If we do this we end up with Option[Option[Int]]
  //       for tileMaxVisitor and Option[Array[Option[Int]]] for multibandTileVisitor due
  //       to the outer wrapper on the PolygonalSummary return type to handle disjoint
  //       geometries. And for some implementations, like StreamingHistogram,
  //       Option[StreamingHistogram] doesn't really make sense.
  def result: R

  def visit(raster: T, col: Int, row: Int): Unit
}

object CellVisitor {
  def apply[T, R](implicit ev: CellVisitor[T, R]): CellVisitor[T, R] = ev
}
