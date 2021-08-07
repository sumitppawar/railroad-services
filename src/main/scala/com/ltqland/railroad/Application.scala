package com.ltqland.railroad

import com.ltqland.railroad.conf.FileConfReader
import com.ltqland.railroad.domain.TownName
import com.ltqland.railroad.network.NetworkBuilder
import com.ltqland.railroad.services.{ ExactlyStops, RailRoadService }

object Application {

  def main(args: Array[String]): Unit = {
    //Example RailRoadService
    val railRoadService =
      FileConfReader("src/main/resources/routes.conf").read
        .map(conf => RailRoadService(NetworkBuilder(conf.roads)))
        .fold(
          error => throw new RuntimeException(error.v),
          identity
        )

    railRoadService.routeDistance(
      List(TownName('A'), TownName('B'), TownName('C'))
    )

    railRoadService.shortestRouteDistance(TownName('A'), TownName('C'))
    railRoadService.tripCount(
      TownName('A'),
      TownName('C'),
      ExactlyStops(4)
    )

  }
}
