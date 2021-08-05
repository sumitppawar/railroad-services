package com.railroad.ltqland.services.distance

import com.railroad.ltqland.BaseSpec
import com.railroad.ltqland.map.TownName
import com.railroad.ltqland.services.route.{ Path, RoutesComputeService }

class ShortestRouteServiceImplSpec extends BaseSpec {
  "def length" should "return shortest route length" in {
    val startTown    = TownName('A')
    val endTown      = TownName('B')
    val routeService = stub[RoutesComputeService]

    (routeService.all _)
      .when(startTown, endTown)
      .returns(
        Map(
          Path(Seq(TownName('A'), TownName('B')))                -> 4,
          Path(Seq(TownName('A'), TownName('c'), TownName('B'))) -> 3
        )
      )

    val actual = ShortestRouteServiceImpl(routeService)
      .length(
        startTown,
        endTown
      )

    actual shouldEqual 3
  }

  it should "return 0 if there is no route exist between two town" in {
    val startTown    = TownName('A')
    val endTown      = TownName('B')
    val routeService = stub[RoutesComputeService]

    (routeService.all _)
      .when(startTown, endTown)
      .returns(
        Map.empty[Path, Int]
      )

    val actual = ShortestRouteServiceImpl(routeService)
      .length(
        startTown,
        endTown
      )

    actual shouldEqual 0
  }
}
