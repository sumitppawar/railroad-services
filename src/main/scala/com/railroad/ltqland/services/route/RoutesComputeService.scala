package com.railroad.ltqland.services.route

import com.railroad.ltqland.map.TownName

trait RoutesComputeService {
  def all(start: TownName, end: TownName): Map[Path, Int]

  def routeCounts(start: TownName, end: TownName, criteria: RoutesComputeCriteria): Int =
    all(start, end)
      .count { tuple =>
        val (path, distance) = tuple
        criteria match {
          case MaximumStops(v) =>
            path.towns.length <= v
          case ExactlyStops(v) =>
            path.towns.length == v
          case DistanceLessThan(v) =>
            distance < v
        }
      }

}

sealed trait RoutesComputeCriteria

case class MaximumStops(v: Int)     extends RoutesComputeCriteria
case class ExactlyStops(v: Int)     extends RoutesComputeCriteria
case class DistanceLessThan(v: Int) extends RoutesComputeCriteria
