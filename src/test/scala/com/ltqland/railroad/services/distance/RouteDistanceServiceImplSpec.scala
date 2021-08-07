package com.ltqland.railroad.services.distance

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.domain.{ Road, TownName }
import com.ltqland.railroad.network.NetworkBuilder

class RouteDistanceServiceImplSpec extends BaseSpec {

  private val map = NetworkBuilder(
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

  "def compute" should "return distance for route given" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute(List(TownName('A'), TownName('E'), TownName('B'), TownName('C'), TownName('D')))

    actualDistance shouldEqual Right(22)
  }

  it should "return 'NO SUCH ROUTE' if no route exist" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute(List(TownName('A'), TownName('B'), TownName('E')))

    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }

  it should "return NO SUCH ROUTE' if only one town is given in route" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute(List(TownName('A')))
    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }

  it should "return NO SUCH ROUTE' if town given in routes are not in map" in {
    val actualDistance = RouteDistanceServiceImpl(map)
      .compute(
        List(
          TownName('A'),
          TownName('E'),
          TownName('B'),
          TownName('C'),
          TownName('D'),
          TownName('X')
        )
      )
    actualDistance shouldEqual Left("NO SUCH ROUTE")
  }
}
