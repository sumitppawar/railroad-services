package com.ltqland.railroad.services.distance

import com.ltqland.railroad.domain.{ OutRoute, Town, TownName }

import scala.collection.mutable

class ShortestRouteServiceImpl(map: Map[TownName, Town]) extends ShortestRouteService {

  def compute(from: TownName, to: TownName): Int = {
    val visitedPath = mutable.HashSet[(TownName, TownName)]()

    def recHelper(
      start: TownName,
      end: TownName,
      distance: Int,
      sameCity: Boolean = false
    ): Int =
      if (!sameCity && start == end)
        distance
      else {
        map
          .get(start)
          .map(_.outRoutes)
          .getOrElse(Set.empty[OutRoute])
          .foldLeft(0) { (currentShort, route) =>
            if (visitedPath.contains((start, route.toTownName)))
              currentShort
            else {
              visitedPath.add(start -> route.toTownName)
              val newDistance = recHelper(
                route.toTownName,
                end,
                distance + route.distance
              )
              if (newDistance != 0 && (currentShort == 0 || newDistance < currentShort)) {
                visitedPath.remove(start -> route.toTownName)
                newDistance
              } else
                currentShort
            }
          }
      }

    recHelper(
      from,
      to,
      distance = 0,
      sameCity = true
    )
  }

}

object ShortestRouteServiceImpl {

  def apply(map: Map[TownName, Town]): ShortestRouteServiceImpl =
    new ShortestRouteServiceImpl(map)
}
