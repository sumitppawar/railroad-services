package com.railroad.ltqland.map

import com.railroad.ltqland.BaseSpec

class RailRoadMapBuilderSpec extends BaseSpec {

  "def build" should "build map with all town" in {
    val stringRoutes = Set("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
    val actual       = RailRoadMapBuilder(stringRoutes).keys
    val expected     = Set(TownName('A'), TownName('B'), TownName('C'), TownName('D'), TownName('E'))
    actual shouldEqual expected
  }

  it should "build map with inRoutes" in {
    val stringRoutes   = Set("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
    val map            = RailRoadMapBuilder(stringRoutes)
    val actualInRoutes = map(TownName('E')).inRoutes
    val expectedInRoutes = Set(
      InRoute(TownName('D'), 6),
      InRoute(TownName('C'), 2),
      InRoute(TownName('A'), 7)
    )
    actualInRoutes shouldEqual expectedInRoutes
  }

  it should "build map with outRoutes" in {
    val stringRoutes    = Set("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
    val map             = RailRoadMapBuilder(stringRoutes)
    val actualOutRoutes = map(TownName('A')).outRoutes
    val expectedOutRoutes = Set(
      OutRoute(TownName('B'), 5),
      OutRoute(TownName('D'), 5),
      OutRoute(TownName('E'), 7)
    )
    actualOutRoutes shouldEqual expectedOutRoutes
  }

}
