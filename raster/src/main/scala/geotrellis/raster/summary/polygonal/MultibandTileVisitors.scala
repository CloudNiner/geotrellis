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

import geotrellis.raster._

object MultibandTileVisitors {

  def multibandTileMaxVisitor: CellVisitor[Raster[MultibandTile], Array[Int]] =
    new CellVisitor[Raster[MultibandTile], Array[Int]] {

      override val empty = Array[Int]()

      override def register(raster: Raster[MultibandTile],
                            col: Int,
                            row: Int,
                            acc: Array[Int]): Array[Int] = {
        val tiles = raster.tile.bands.toArray
        val maxValues: Array[Int] = acc ++ Array.fill[Int](tiles.size - acc.size)(Int.MinValue)
        val tilesWithMax: Array[(Tile, Int)] = tiles.zip(maxValues)
        tilesWithMax.map {
          case (tile: Tile, max: Int) =>
            val value = tile.get(col, row)
            if (isData(value) && value > max) {
              value
            } else {
              max
            }
        }
      }
    }
}
