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

package geotrellis.raster.summary.visitors

import geotrellis.raster._

class TileMaxVisitor extends CellVisitor[Raster[Tile], Option[Int]] {
  private var accumulator: Option[Int] = None

  def result: Option[Int] = accumulator

  def visit(raster: Raster[Tile],
            col: Int,
            row: Int): Unit = {
    val value = raster.tile.get(col, row)
    accumulator = result match {
      case Some(max) if (isData(value) && value > max) => Some(value)
      case None if isData(value) => Some(value)
      case _ => result
    }
  }
}
