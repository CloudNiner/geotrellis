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

package geotrellis.raster.summary.polygonal.visitors

import geotrellis.raster._
import geotrellis.raster.summary.GridVisitor

// TODO: Docs if public
// TODO: Refactor for performance
abstract class MultibandTileFoldingVisitor
    extends GridVisitor[Raster[MultibandTile], Array[Option[Double]]] {

  private var initialized = false
  private var accumulator = Array[Double]()

  def result: Array[Option[Double]] = {
    accumulator.map { value =>
      if (isData(value)) Some(value) else None
    }
  }

  def visit(raster: Raster[MultibandTile], col: Int, row: Int): Unit = {
    val tiles = raster.tile.bands.toArray
    if (!initialized) {
      accumulator = Array.fill[Double](tiles.size)(Double.NaN)
      initialized = true
    }
    accumulator = tiles.zip(accumulator).map {
      case (tile: Tile, accum: Double) =>
        val newValue = tile.getDouble(col, row)
        if (isData(newValue)) {
          if (isData(accum)) {
            fold(accum, newValue)
          } else {
            newValue
          }
        } else {
          accum
        }
    }
  }

  def fold(accum: Double, newValue: Double): Double
}
