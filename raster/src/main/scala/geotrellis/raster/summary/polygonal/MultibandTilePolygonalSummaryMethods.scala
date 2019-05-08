package geotrellis.raster.summary.polygonal

import geotrellis.raster.MultibandTile
import geotrellis.raster.histogram.Histogram
import geotrellis.util.MethodExtensions
import geotrellis.vector.{MultiPolygon, Polygon}

trait MultibandTilePolygonalSummaryMethods extends MethodExtensions[MultibandTile] {
  /**
    * Given an extent and a Polygon, compute the histogram of the tile
    * values contained within.
    */
  def polygonalHistogram(geom: Polygon): Array[Histogram[Int]] = ???

  /**
    * Given an extent and a MultiPolygon, compute the histogram of the
    * tile values contained within.
    */
  def polygonalHistogram(geom: MultiPolygon): Array[Histogram[Int]] = ???

  /**
    * Given an extent and a Polygon, compute the histogram of the tile
    * values contained within.
    */
  def polygonalHistogramDouble(geom: Polygon): Array[Histogram[Double]] = ???

  /**
    * Given an extent and a MultiPolygon, compute the histogram of the
    * tile values contained within.
    */
  def polygonalHistogramDouble(geom: MultiPolygon): Array[Histogram[Double]] = ???

  /**
    * Given an extent and a Polygon, compute the maximum of the tile
    * values contained within.
    */
  def polygonalMax(geom: Polygon): Array[Int] = ???

  /**
    * Given an extent and a MultiPolygon, compute the maximum of the
    * tile values contained within.
    */
  def polygonalMax(geom: MultiPolygon): Array[Int] = ???

  /**
    * Given an extent and a Polygon, compute the maximum of the tile
    * values contained within.
    */
  def polygonalMaxDouble(geom: Polygon): Array[Double] = ???

  /**
    * Given an extent and a MultiPolygon, compute the maximum of the
    * tile values contained within.
    */
  def polygonalMaxDouble(geom: MultiPolygon): Array[Double] = ???

  /**
    * Given an extent and a Polygon, compute the minimum of the tile
    * values contained within.
    */
  def polygonalMin(geom: Polygon): Array[Int] = ???

  /**
    * Given an extent and a MultiPolygon, compute the minimum of the
    * tile values contained within.
    */
  def polygonalMin(geom: MultiPolygon): Array[Int] = ???

  /**
    * Given an extent and a Polygon, compute the minimum of the tile
    * values contained within.
    */
  def polygonalMinDouble(geom: Polygon): Array[Double] = ???

  /**
    * Given an extent and a MultiPolygon, compute the minimum of the
    * tile values contained within.
    */
  def polygonalMinDouble(geom: MultiPolygon): Array[Double] = ???

  /**
    * Given an extent and a Polygon, compute the mean of the tile
    * values contained within.
    */
  def polygonalMean(geom: Polygon): Array[Double] = ???

  /**
    * Given an extent and a MultiPolygon, compute the mean of the tile
    * values contained within.
    */
  def polygonalMean(geom: MultiPolygon): Array[Double] = ???

  /**
    * Given an extent and a Polygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSum(geom: Polygon): Array[Long] = ???

  /**
    * Given an extent and a MultiPolygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSum(geom: MultiPolygon): Array[Long] = ???

  /**
    * Given an extent and a Polygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSumDouble(geom: Polygon): Array[Double] = ???

  /**
    * Given an extent and a MultiPolygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSumDouble(geom: MultiPolygon): Array[Double] = ???
}
