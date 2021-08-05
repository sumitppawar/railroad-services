package com.railroad.ltqland

import com.railroad.ltqland.conf.FromFileRailRoadConfReader
import com.railroad.ltqland.map.{ RailRoadMapBuilder, Town, TownName }
import com.railroad.ltqland.services.distance.{
  RouteDistanceService,
  RouteDistanceServiceImpl,
  ShortestRouteService,
  ShortestRouteServiceImpl
}
import com.railroad.ltqland.services.route._

object Application {

  def main(args: Array[String]): Unit =
    FromFileRailRoadConfReader("src/main/resources/routes.conf").read
      .fold(
        error => println(error.v),
        conf => {
          println(s"Input map ${conf.strRoutes.mkString(",")} ")

          val map                  = RailRoadMapBuilder(conf.strRoutes)
          val routeDistanceService = RoutesComputeServiceImpl(map)
          computeAndPrintOnConsole(
            map,
            routeDistanceService,
            RouteDistanceServiceImpl(map),
            ShortestRouteServiceImpl(routeDistanceService)
          )
        }
      )

  def computeAndPrintOnConsole(
    map: Map[TownName, Town],
    routesComputeService: RoutesComputeService,
    routeDistanceService: RouteDistanceService,
    shortestPathService: ShortestRouteService
  ): Unit = {

    println("Output")

    printToConsole(
      "1. The distance of the route A-B-C.",
      () => routeDistanceService.compute("A-B-C").fold(identity, _.toString)
    )
    printToConsole(
      "2. The distance of the route A-D",
      () => routeDistanceService.compute("A-D").fold(identity, _.toString)
    )
    printToConsole(
      "3. The distance of the route A-D-C",
      () => routeDistanceService.compute("A-D-C").fold(identity, _.toString)
    )
    printToConsole(
      "4. The distance of the route A-E-B-C-D",
      () => routeDistanceService.compute("A-E-B-C-D").fold(identity, _.toString)
    )
    printToConsole(
      "5. The distance of the route A-E-D",
      () => routeDistanceService.compute("A-E-D").fold(identity, _.toString)
    )

    printToConsole(
      "6. The number of trips starting at C and ending at C with a maximum of 3 stops",
      () =>
        routesComputeService.routeCounts(
          TownName('C'),
          TownName('C'),
          MaximumStops(3)
        )
    )

    printToConsole(
      "7. The number of trips starting at A and ending at C with exactly 4 stops",
      () =>
        routesComputeService.routeCounts(
          TownName('A'),
          TownName('C'),
          ExactlyStops(4)
        )
    )

    printToConsole(
      "8. The length of the shortest route (in terms of distance to travel) from A to C",
      () =>
        shortestPathService.length(
          TownName('A'),
          TownName('C')
        )
    )
    printToConsole(
      "9. The length of the shortest route (in terms of distance to travel) from B to B",
      () =>
        shortestPathService.length(
          TownName('B'),
          TownName('B')
        )
    )

    printToConsole(
      "10. The number of different routes from C to C with a distance of less than 30",
      () =>
        routesComputeService.routeCounts(
          TownName('C'),
          TownName('C'),
          DistanceLessThan(30)
        )
    )

  }

  private def printToConsole(msg: String, f: () => Any): Unit = {
    println(msg)
    println(s"Result: ${f()}")
  }

}
