package com.ltqland.railroad.domain

final case class Town(
  name: TownName,
  outRoutes: Set[OutRoute]
)

final case class OutRoute(toTownName: TownName, distance: Int)
