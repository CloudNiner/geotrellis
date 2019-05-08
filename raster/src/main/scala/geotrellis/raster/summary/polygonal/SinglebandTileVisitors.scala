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

import cats.Monoid
import geotrellis.raster._
import geotrellis.raster.histogram.{FastMapHistogram, StreamingHistogram}

object SinglebandTileVisitors {
  def tileMaxVisitor: CellVisitor[Raster[Tile], Int] =
    new CellVisitor[Raster[Tile], Int] {

      override val empty = Int.MinValue

      override def register(raster: Raster[Tile],
                            col: Int,
                            row: Int,
                            acc: Int): Int = {
        val v = raster.tile.get(col, row)
        if (isData(v) && v > acc) {
          v
        } else {
          acc
        }
      }
    }

  def tileFastMapHistogramVisitor: CellVisitor[Raster[Tile], FastMapHistogram] =
    new CellVisitor[Raster[Tile], FastMapHistogram] {

      override def empty = Monoid[FastMapHistogram].empty

      override def register(raster: Raster[Tile],
                            col: Int,
                            row: Int,
                            acc: FastMapHistogram): FastMapHistogram = {
        val v = raster.tile.get(col, row)
        // TODO: This check was in the old methods. Do we want to keep it here?
        if (isData(v)) acc.countItem(v, count = 1)
        acc
      }
    }

  def tileStreamingHistogramVisitor
    : CellVisitor[Raster[Tile], StreamingHistogram] =
    new CellVisitor[Raster[Tile], StreamingHistogram] {

      override def empty = Monoid[StreamingHistogram].empty

      override def register(raster: Raster[Tile],
                            col: Int,
                            row: Int,
                            acc: StreamingHistogram): StreamingHistogram = {
        val v = raster.tile.getDouble(col, row)
        // TODO: This check was in the old methods. Do we want to keep it here?
        if (isData(v)) acc.countItem(v, count = 1)
        acc
      }
    }
}
