package com.ltqland.railroad.conf

trait RailRoadConfReader {
  def read: Either[ConfReaderError, RailRoadConf]
}

final case class ConfReaderError(v: String)
