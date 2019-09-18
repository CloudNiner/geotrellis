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

package geotrellis.raster.geotiff

import geotrellis.raster._
import geotrellis.raster.io.geotiff._
import org.scalatest._

class GeoTiffMultibandTileSpec extends FunSpec with GeoTiffTestUtils {
  describe("GeoTiffMultibandTiles should crop correctly") {
    it("should crop a BitGeoTiffMultibandTile") {
      val path = geoTiffPath("3bands/bit/3bands-striped-band.tif")
      val geotiff: MultibandGeoTiff = GeoTiffReader.readMultiband(path)
      println(geotiff.cellType)
      val cropped = geotiff.tile.convert(BitCellType).crop(0, 0, 10, 20)
      // TODO: Come up with an actual cropped multiband tile to compare against
      assert(geotiff.tile.bandCount == cropped.bandCount)
    }
  }
}
