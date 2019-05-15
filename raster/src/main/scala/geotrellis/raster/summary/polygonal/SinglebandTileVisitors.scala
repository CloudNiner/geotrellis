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

/**
  * TODO: MOVE
  *       - CellVisitor.scala to geotrellis.raster
  *       - Visitor implementations as classes in separate files under
  *         geotrellis.raster.summary.visitors
  *
  * TODO: DELETE *TilePolygonalSummaryMethods
  *       Reasons to keep: tab completion for simple polygonal summary implementations
  *                        (dev experience)
  *       Reasons to delete: All boilerplate. We've changed the method signature so we don't
  *                          save client code refactors.
  * TODO: Consider MaxVisitor implicits to group implementations for Raster[Tile|MultibandTile]
  *       See below.
  */
object SinglebandTileVisitors {

  def tileMaxVisitor: CellVisitor[Raster[Tile], Int] =
    new CellVisitor[Raster[Tile], Int] {

      private var accumulator: Int = NODATA

      def result = accumulator

      def visit(raster: Raster[Tile],
                col: Int,
                row: Int): Unit = {
        val v = raster.tile.get(col, row)
        if (isData(v) && (v == NODATA || v > result)) {
          accumulator = v
        }
      }
    }

  def tileFastMapHistogramVisitor: CellVisitor[Raster[Tile], FastMapHistogram] =
    new CellVisitor[Raster[Tile], FastMapHistogram] {

      private var accumulator = Monoid[FastMapHistogram].empty

      def result = accumulator

      def visit(raster: Raster[Tile],
                         col: Int,
                         row: Int): Unit = {
        val v = raster.tile.get(col, row)
        // TODO: This check was in the old methods. Do we want to keep it here?
        if (isData(v)) accumulator.countItem(v, count = 1)
      }
    }

  def tileStreamingHistogramVisitor
    : CellVisitor[Raster[Tile], StreamingHistogram] =
    new CellVisitor[Raster[Tile], StreamingHistogram] {

      private var accumulator = Monoid[StreamingHistogram].empty

      def result = accumulator

      def visit(raster: Raster[Tile],
                         col: Int,
                         row: Int): Unit = {
        val v = raster.tile.getDouble(col, row)
        // TODO: This check was in the old methods. Do we want to keep it here?
        if (isData(v)) accumulator.countItem(v, count = 1)
      }
    }
}
