package com.ltqland.railroad.services.distance

import com.ltqland.railroad.domain.TownName

trait RouteDistanceService {
  def compute(route: List[TownName]): Either[String, Int]
}
