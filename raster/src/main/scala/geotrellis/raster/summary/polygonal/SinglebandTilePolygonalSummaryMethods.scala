package geotrellis.raster.summary.polygonal

import geotrellis.raster.Tile
import geotrellis.raster.histogram.Histogram
import geotrellis.util.MethodExtensions
import geotrellis.vector.{MultiPolygon, Polygon}

trait SinglebandTilePolygonalSummaryMethods extends MethodExtensions[Tile] {

  /**
    * Given an extent and a Polygon, compute the histogram of the tile
    * values contained within.
    */
  def polygonalHistogram(geom: Polygon): Histogram[Int] = ???

  /**
    * Given an extent and a MultiPolygon, compute the histogram of the
    * tile values contained within.
    */
  def polygonalHistogram(geom: MultiPolygon): Histogram[Int] = ???

  /**
    * Given an extent and a Polygon, compute the histogram of the tile
    * values contained within.
    */
  def polygonalHistogramDouble(geom: Polygon): Histogram[Double] = ???

  /**
    * Given an extent and a MultiPolygon, compute the histogram of the
    * tile values contained within.
    */
  def polygonalHistogramDouble(geom: MultiPolygon): Histogram[Double] = ???

  /**
    * Given an extent and a Polygon, compute the maximum of the tile
    * values contained within.
    */
  def polygonalMax(geom: Polygon): Int = ???

  /**
    * Given an extent and a MultiPolygon, compute the maximum of the
    * tile values contained within.
    */
  def polygonalMax(geom: MultiPolygon): Int = ???

  /**
    * Given an extent and a Polygon, compute the maximum of the tile
    * values contained within.
    */
  def polygonalMaxDouble(geom: Polygon): Double = ???

  /**
    * Given an extent and a MultiPolygon, compute the maximum of the
    * tile values contained within.
    */
  def polygonalMaxDouble(geom: MultiPolygon): Double = ???

  /**
    * Given an extent and a Polygon, compute the minimum of the tile
    * values contained within.
    */
  def polygonalMin(geom: Polygon): Int = ???

  /**
    * Given an extent and a MultiPolygon, compute the minimum of the
    * tile values contained within.
    */
  def polygonalMin(geom: MultiPolygon): Int = ???

  /**
    * Given an extent and a Polygon, compute the minimum of the tile
    * values contained within.
    */
  def polygonalMinDouble(geom: Polygon): Double = ???

  /**
    * Given an extent and a MultiPolygon, compute the minimum of the
    * tile values contained within.
    */
  def polygonalMinDouble(geom: MultiPolygon): Double = ???

  /**
    * Given an extent and a Polygon, compute the mean of the tile
    * values contained within.
    */
  def polygonalMean(geom: Polygon): Double = ???

  /**
    * Given an extent and a MultiPolygon, compute the mean of the tile
    * values contained within.
    */
  def polygonalMean(geom: MultiPolygon): Double = ???

  /**
    * Given an extent and a Polygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSum(geom: Polygon): Long = ???

  /**
    * Given an extent and a MultiPolygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSum(geom: MultiPolygon): Long = ???

  /**
    * Given an extent and a Polygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSumDouble(geom: Polygon): Double = ???

  /**
    * Given an extent and a MultiPolygon, compute the sum of the tile
    * values contained within.
    */
  def polygonalSumDouble(geom: MultiPolygon): Double = ???
}
