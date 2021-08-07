package com.ltqland.railroad.network

import com.ltqland.railroad.domain.{ OutRoute, Road, Town, TownName }

case object NetworkBuilder {

  def apply(roads: Set[Road]): Map[TownName, Town] =
    roads
      .foldLeft(Map.empty[TownName, Town]) { (map, road) =>
        updateOutRoutes(map, road.from, road.to, road.distance) + (road.to ->
          map
            .get(road.to)
            .fold(
              Town(road.from, Set.empty)
            )(identity))

      }

  private def updateOutRoutes(
    map: Map[TownName, Town],
    fromTownName: TownName,
    toTownName: TownName,
    distance: Int
  ): Map[TownName, Town] =
    map + (fromTownName ->
      map
        .get(fromTownName)
        .fold(
          Town(fromTownName, Set(OutRoute(toTownName, distance)))
        )(town =>
          town.copy(
            outRoutes = town.outRoutes + OutRoute(toTownName, distance)
          )
        ))

}
