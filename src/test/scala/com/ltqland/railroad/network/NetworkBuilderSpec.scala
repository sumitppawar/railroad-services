package com.ltqland.railroad.network

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.domain.{ OutRoute, Road, TownName }

class NetworkBuilderSpec extends BaseSpec {

  "def build" should "build map with all town" in {
    val actual = NetworkBuilder(
      Set(
        Road(TownName('A'), TownName('B'), 5),
        Road(TownName('B'), TownName('C'), 4),
        Road(TownName('C'), TownName('D'), 8),
        Road(TownName('D'), TownName('C'), 8),
        Road(TownName('D'), TownName('E'), 6),
        Road(TownName('A'), TownName('D'), 5),
        Road(TownName('C'), TownName('E'), 2),
        Road(TownName('E'), TownName('B'), 3),
        Road(TownName('A'), TownName('E'), 7)
      )
    ).keys

    val expected =
      Set(TownName('A'), TownName('B'), TownName('C'), TownName('D'), TownName('E'))
    actual shouldEqual expected
  }

  it should "build map with outRoutes" in {
    val exampleMap = NetworkBuilder(
      Set(
        Road(TownName('A'), TownName('B'), 5),
        Road(TownName('B'), TownName('C'), 4),
        Road(TownName('C'), TownName('D'), 8),
        Road(TownName('D'), TownName('C'), 8),
        Road(TownName('D'), TownName('E'), 6),
        Road(TownName('A'), TownName('D'), 5),
        Road(TownName('C'), TownName('E'), 2),
        Road(TownName('E'), TownName('B'), 3),
        Road(TownName('A'), TownName('E'), 7)
      )
    )

    val actualOutRoutes = exampleMap(TownName('A')).outRoutes
    val expectedOutRoutes = Set(
      OutRoute(TownName('B'), 5),
      OutRoute(TownName('D'), 5),
      OutRoute(TownName('E'), 7)
    )
    actualOutRoutes shouldEqual expectedOutRoutes
  }

}
