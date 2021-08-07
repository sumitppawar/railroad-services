package com.ltqland.railroad.services.distance

import com.ltqland.railroad.domain.{ OutRoute, Town, TownName }

class RouteDistanceServiceImpl(map: Map[TownName, Town]) extends RouteDistanceService {

  override def compute(route: List[TownName]): Either[String, Int] = {

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

    val distance =
      recHelper(
        route.tail,
        map.get(route.head).map(_.outRoutes).getOrElse(Set.empty[OutRoute]),
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
