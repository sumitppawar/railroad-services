package com.railroad.ltqland.map

final case class TownName(name: Char)

final case class Town(
  name: TownName,
  inRoutes: Set[InRoute],
  outRoutes: Set[OutRoute]
)

final case class InRoute(fromTownName: TownName, distance: Int)
final case class OutRoute(toTownName: TownName, distance: Int)
