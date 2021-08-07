package com.ltqland.railroad.services.distance

import com.ltqland.railroad.domain.TownName

trait ShortestRouteService {
  def compute(from: TownName, to: TownName): Int
}
