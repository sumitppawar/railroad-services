package com.railroad.ltqland.services.distance

import com.railroad.ltqland.BaseSpec
import com.railroad.ltqland.map.RailRoadMapBuilder

class RouteDistanceServiceImplSpec extends BaseSpec {

  private val mapRoutes = Set("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
  private val map       = RailRoadMapBuilder(mapRoutes)

  "def compute" should "return distance for route given" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute("A-E-B-C-D")
    actualDistance shouldEqual Right(22)
  }

  it should "return 'NO SUCH ROUTE' if no route exist" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute("A-B-E")
    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }

  it should "return NO SUCH ROUTE' if only one town is given in route" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute("A")
    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }

  it should "return NO SUCH ROUTE' if town given in routes are not in map" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute("A-E-B-C-D-X")
    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }
}
