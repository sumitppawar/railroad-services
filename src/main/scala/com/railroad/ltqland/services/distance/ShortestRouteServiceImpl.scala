package com.railroad.ltqland.services.distance

import com.railroad.ltqland.map.TownName
import com.railroad.ltqland.services.route.RoutesComputeService

class ShortestRouteServiceImpl(routeService: RoutesComputeService) extends ShortestRouteService {

  override def length(start: TownName, end: TownName): Int =
    routeService
      .all(start, end)
      .values
      .minOption
      .getOrElse(0)
}

object ShortestRouteServiceImpl {

  def apply(routeService: RoutesComputeService): ShortestRouteServiceImpl =
    new ShortestRouteServiceImpl(routeService)
}
