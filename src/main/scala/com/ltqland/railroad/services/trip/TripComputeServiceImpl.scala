package com.ltqland.railroad.services.trip

import com.ltqland.railroad.domain.{ OutRoute, Town, TownName }

class TripComputeServiceImpl(map: Map[TownName, Town]) extends TripComputeService {

  override def compute(
    from: TownName,
    to: TownName,
    filter: (String, Int) => Boolean,
    stopCriteria: (String, Int) => Boolean
  ): Int = {

    def recHelper(
      start: TownName,
      end: TownName,
      distance: Int,
      sameCity: Boolean,
      path: String
    ): Int =
      if (stopCriteria(path, distance))
        0
      else {
        val tripCount =
          if (!sameCity && start == end && filter(path, distance)) 1
          else 0

        map
          .get(start)
          .map(_.outRoutes)
          .getOrElse(Set.empty[OutRoute])
          .foldLeft(tripCount) { (tripCount, nextTown) =>
            tripCount + recHelper(
              nextTown.toTownName,
              end,
              distance + nextTown.distance,
              sameCity = false,
              s"$path${nextTown.toTownName.name}"
            )
          }
      }

    recHelper(from, to, 0, from == to, s"")
  }
}

object TripComputeServiceImpl {

  def apply(map: Map[TownName, Town]): TripComputeServiceImpl =
    new TripComputeServiceImpl(map)
}
