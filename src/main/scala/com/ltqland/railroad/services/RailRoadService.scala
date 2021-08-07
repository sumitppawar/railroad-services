package com.ltqland.railroad.services

import com.ltqland.railroad.domain.{ Town, TownName }
import com.ltqland.railroad.services.distance.{
  RouteDistanceService,
  RouteDistanceServiceImpl,
  ShortestRouteService,
  ShortestRouteServiceImpl
}
import com.ltqland.railroad.services.trip.{ TripComputeService, TripComputeServiceImpl }

class RailRoadService(
  routeDistanceService: RouteDistanceService,
  shortestRouteService: ShortestRouteService,
  tripComputeService: TripComputeService
) {

  def shortestRouteDistance(from: TownName, to: TownName): Int =
    shortestRouteService.compute(from, to)

  def routeDistance(route: List[TownName]): Either[String, Int] =
    routeDistanceService.compute(route)

  def tripCount(
    from: TownName,
    to: TownName,
    computeCriteria: TripComputeCriteria
  ): Int = computeCriteria match {
    case MaximumStops(v) =>
      tripComputeService.compute(
        from,
        to,
        (path, _) => path.length <= v,
        (path, _) => path.length > v
      )

    case ExactlyStops(v) =>
      tripComputeService.compute(
        from,
        to,
        (path, _) => path.length == v,
        (path, _) => path.length > v
      )
    case DistanceLessThan(v) =>
      tripComputeService.compute(
        from,
        to,
        (_, distance) => distance < v,
        (_, distance) => distance >= v
      )

  }

}

object RailRoadService {

  def apply(
    townRailNetwork: Map[TownName, Town]
  ): RailRoadService = new RailRoadService(
    RouteDistanceServiceImpl(townRailNetwork),
    ShortestRouteServiceImpl(townRailNetwork),
    TripComputeServiceImpl(townRailNetwork)
  )
}
