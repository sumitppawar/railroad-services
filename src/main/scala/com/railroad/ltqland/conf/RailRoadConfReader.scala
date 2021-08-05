package com.railroad.ltqland.conf

trait RailRoadConfReader {
  def read: Either[RailRoadConfReaderError, RailRoadConf]
}

final case class RailRoadConfReaderError(v: String)
