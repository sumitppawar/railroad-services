package com.ltqland.railroad.services.distance

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.domain.TownName

class ShortestRouteServiceImplSpec extends BaseSpec {

  "def length" should "return shortest route length" in {
    val startTown = TownName('A')
    val endTown   = TownName('C')
    val actual = ShortestRouteServiceImpl(exampleMap)
      .compute(
        startTown,
        endTown
      )

    actual shouldEqual 9
  }

  it should "return 0 if there is no route exist between two town" in {
    val startTown = TownName('A')
    val endTown   = TownName('A')

    val actual = ShortestRouteServiceImpl(exampleMap)
      .compute(
        startTown,
        endTown
      )

    actual shouldEqual 0
  }

  it should "return shortest path for same start and end destination" in {
    val startTown = TownName('C')
    val endTown   = TownName('C')

    val actual = ShortestRouteServiceImpl(exampleMap)
      .compute(
        startTown,
        endTown
      )

    actual shouldEqual 9
  }
}
