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

package geotrellis.raster

/** TODO: DELETE *TilePolygonalSummaryMethods
  *       Reasons to keep: tab completion for simple polygonal summary implementations
  *                        (dev experience)
  *       Reasons to delete: All boilerplate. We've changed the method signature so we don't
  *                          save client code refactors.
  * TODO: Consider MaxVisitor implicits to group implementations for Raster[Tile|MultibandTile]
  *       See below.
  */

/** Visitor for cell values of T that should record their state of R
  *
  * The user should implement concrete subclasses that update the value of `result` as
  * necessary on each call to `visit(raster: T, col: Int, row: Int)`
  */
trait CellVisitor[-T, R] {
  def result: R

  def visit(raster: T, col: Int, row: Int): Unit
}

object CellVisitor {
  def apply[T, R](implicit ev: CellVisitor[T, R]): CellVisitor[T, R] = ev
}
