package com.ltqland.railroad.conf

import com.ltqland.railroad.domain.{ Road, TownName }

import scala.io.Source
import scala.util.Try

class FileConfReader(filePath: String) extends RailRoadConfReader {

  override def read: Either[ConfReaderError, RailRoadConf] =
    Try {
      val source = Source.fromFile(filePath)
      val routes = source.getLines.toSeq.head
        .split(",")
        .toSet[String]
        .map(road =>
          Road(
            TownName(road(0)),
            TownName(road(1)),
            road(2).toString.toInt
          )
        )

      source.close()
      RailRoadConf(routes)
    }.fold(
      th => Left(ConfReaderError(th.getMessage)),
      value => Right(value)
    )
}

object FileConfReader {

  def apply(filePath: String): FileConfReader =
    new FileConfReader(filePath)
}
