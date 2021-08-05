package com.railroad.ltqland.conf

import scala.io.Source
import scala.util.Try

class FromFileRailRoadConfReader(filePath: String) extends RailRoadConfReader {

  override def read: Either[RailRoadConfReaderError, RailRoadConf] =
    Try {
      val source = Source.fromFile(filePath)
      val routes = source.getLines.toSeq.head
        .split(",")
        .toSet

      source.close()
      RailRoadConf(routes)

    }.fold(
      th => Left(RailRoadConfReaderError(th.getMessage)),
      value => Right(value)
    )
}

object FromFileRailRoadConfReader {

  def apply(filePath: String): FromFileRailRoadConfReader =
    new FromFileRailRoadConfReader(filePath)
}
