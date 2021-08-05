package com.railroad.ltqland.services.distance

import com.railroad.ltqland.map.TownName

trait ShortestRouteService {
  def length(start: TownName, end: TownName): Int
}
