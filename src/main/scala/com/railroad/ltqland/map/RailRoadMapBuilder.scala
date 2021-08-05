package com.railroad.ltqland.map

object RailRoadMapBuilder {

  def apply(stringRoutes: Set[String]): Map[TownName, Town] =
    stringRoutes
      .foldLeft(Map.empty[TownName, Town]) { (map, stringRoute) =>
        val fromTownName = TownName(stringRoute(0))
        val distance     = stringRoute(2).toString.toInt
        val toTownName   = TownName(stringRoute(1))

        updateToTownInRoutes(
          updateFromTownOutRoutes(map, fromTownName, toTownName, distance),
          fromTownName,
          toTownName,
          distance
        )

      }

  private def updateFromTownOutRoutes(
    map: Map[TownName, Town],
    fromTownName: TownName,
    toTownName: TownName,
    distance: Int
  ): Map[TownName, Town] =
    map + (fromTownName ->
      map
        .get(fromTownName)
        .fold(
          Town(fromTownName, Set.empty, Set(OutRoute(toTownName, distance)))
        )(town =>
          town.copy(
            outRoutes = town.outRoutes + OutRoute(toTownName, distance)
          )
        ))

  private def updateToTownInRoutes(
    map: Map[TownName, Town],
    fromTownName: TownName,
    toTownName: TownName,
    distance: Int
  ): Map[TownName, Town] =
    map + (toTownName ->
      map
        .get(toTownName)
        .fold(
          Town(toTownName, Set(InRoute(fromTownName, distance)), Set.empty)
        )(town =>
          town.copy(
            inRoutes = town.inRoutes + InRoute(fromTownName, distance)
          )
        ))
}
