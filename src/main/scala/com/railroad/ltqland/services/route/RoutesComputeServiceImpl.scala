package com.railroad.ltqland.services.route

import com.railroad.ltqland.map.{ Town, TownName }

class RoutesComputeServiceImpl(map: Map[TownName, Town]) extends RoutesComputeService {

  override def all(start: TownName, end: TownName): Map[Path, Int] = Map(
    Path(Seq()) -> 0
  )
}

object RoutesComputeServiceImpl {

  def apply(map: Map[TownName, Town]): RoutesComputeServiceImpl =
    new RoutesComputeServiceImpl(map)
}
