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

import cats.Monoid
import geotrellis.raster._
import geotrellis.raster.histogram.StreamingHistogram

class TileStreamingHistogramVisitor extends CellVisitor[Raster[Tile], StreamingHistogram] {
  private var accumulator = Monoid[StreamingHistogram].empty

  def result: StreamingHistogram = accumulator

  def visit(raster: Raster[Tile], col: Int, row: Int): Unit = {
    val v = raster.tile.getDouble(col, row)
    if (isData(v)) accumulator.countItem(v, count = 1)
  }
}
