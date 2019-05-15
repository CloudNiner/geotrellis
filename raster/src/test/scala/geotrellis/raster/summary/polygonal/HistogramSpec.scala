/*
 * Copyright 2016 Azavea
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
import geotrellis.raster.histogram.{FastMapHistogram, StreamingHistogram}
import geotrellis.raster.summary.polygonal.SinglebandTileVisitors._
import geotrellis.vector._
import geotrellis.raster.testkit._
import org.scalatest._

class HistogramSpec
    extends FunSpec
    with Matchers
    with RasterMatchers
    with TileBuilders {

  describe("zonalHistogram") {
    val arr = Array.fill(40 * 40)(1.0)
    val rs = createRaster(arr, 40, 40)
    val tile = rs.tile
    val zone = Extent(10, -10, 50, 10).toPolygon

    val multibandTile = MultibandTile(tile, tile, tile)

    it("computes Int Histogram for Singleband") {
      val result = rs.polygonalSummary[FastMapHistogram](zone, tileFastMapHistogramVisitor)

      result.get.itemCount(1) should equal(40)
      result.get.itemCount(2) should equal(0)
    }

//    it("computes Histogram for Multiband") {
//      val result = multibandTile.polygonalHistogram(extent, zone)
//
//      result map { r =>
//        r.itemCount(1) should equal(40)
//        r.itemCount(2) should equal(0)
//      }
//    }

    it("computes double Histogram for Singleband") {
      val result =
        rs.polygonalSummary[StreamingHistogram](zone, tileStreamingHistogramVisitor)

      result.get.itemCount(1) should equal(40)
      result.get.itemCount(2) should equal(0)
    }

//    it("computes double Histogram for Multiband") {
//      val result = multibandTile.polygonalHistogramDouble(extent, zone)
//
//      result map { r =>
//        r.itemCount(1) should equal(40)
//        r.itemCount(2) should equal(0)
//      }
//    }
  }
}
