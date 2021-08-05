package com.railroad.ltqland.services.distance

import com.railroad.ltqland.map.{ OutRoute, Town, TownName }

class RouteDistanceServiceImpl(map: Map[TownName, Town]) extends RouteDistanceService {

  override def compute(route: String): Either[String, Int] = {

    def recHelper(nextTowns: List[TownName], outRoutes: Set[OutRoute], distance: Int): Int =
      nextTowns match {
        case Nil                    => distance
        case _ if outRoutes.isEmpty => distance
        case _ =>
          outRoutes
            .find(_.toTownName == nextTowns.head)
            .fold(
              0
            )(outRoute =>
              recHelper(
                nextTowns.tail,
                map.get(nextTowns.head).map(_.outRoutes).getOrElse(Set.empty[OutRoute]),
                distance + outRoute.distance
              )
            )
      }

    val townsToVisit = route
      .split("-")
      .map(townName => TownName(townName.head))
      .toList
    val distance =
      recHelper(
        townsToVisit.tail,
        map.get(townsToVisit.head).map(_.outRoutes).getOrElse(Set.empty[OutRoute]),
        0
      )
    if (distance == 0)
      Left("NO SUCH ROUTE")
    else
      Right(distance)
  }
}

object RouteDistanceServiceImpl {
  def apply(map: Map[TownName, Town]): RouteDistanceServiceImpl = new RouteDistanceServiceImpl(map)
}
