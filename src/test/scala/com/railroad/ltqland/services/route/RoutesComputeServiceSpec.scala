package com.railroad.ltqland.services.route

import com.railroad.ltqland.BaseSpec
import com.railroad.ltqland.map.TownName

class RoutesComputeServiceSpec extends BaseSpec {

  private val startTown = TownName('A')
  private val endTown   = TownName('B')

  private val routesComputeService = new RoutesComputeService {

    override def all(start: TownName, end: TownName): Map[Path, Int] = Map(
      Path(Seq(TownName('A'), TownName('B')))                               -> 2,
      Path(Seq(TownName('A'), TownName('D'), TownName('B')))                -> 4,
      Path(Seq(TownName('A'), TownName('C'), TownName('B')))                -> 4,
      Path(Seq(TownName('A'), TownName('E'), TownName('C'), TownName('B'))) -> 10,
      Path(Seq(TownName('A'), TownName('E'), TownName('F'), TownName('B'))) -> 14
    )
  }

  "def routeCounts" should "return route counts with criteria MaximumStops" in {
    val actual = routesComputeService.routeCounts(
      startTown,
      endTown,
      MaximumStops(2)
    )
    actual shouldEqual 1
  }

  it should "return route counts with criteria ExactlyStops" in {
    val actual = routesComputeService.routeCounts(
      startTown,
      endTown,
      ExactlyStops(4)
    )
    actual shouldEqual 2
  }

  it should "return route counts with criteria DistanceLessThan" in {
    val actual = routesComputeService.routeCounts(
      startTown,
      endTown,
      DistanceLessThan(2)
    )
    actual shouldEqual 0
  }
}
