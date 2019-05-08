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

import geotrellis.raster.Tile

/** Visitor for  cell values of T that may record its state in R
  * Note: R instances may be mutable and re-used when adding cell values
  */
trait CellVisitor[-T, R] {

  def empty: R

  // TODO: Maybe return unit instead, since we're mutating acc
  def register(raster: T, col: Int, row: Int, acc: R): R
}

object CellVisitor {
  def apply[T, R](implicit ev: CellVisitor[T, R]) = ev
}
