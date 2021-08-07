package com.ltqland.railroad.services

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.conf.FileConfReader
import com.ltqland.railroad.domain.TownName
import com.ltqland.railroad.network.NetworkBuilder

class RailRoadServiceSpec extends BaseSpec {

  private val railRoadService = FileConfReader("src/test/resources/routes-test.conf").read
    .map(conf => RailRoadService(NetworkBuilder(conf.roads)))
    .fold(
      error => throw new RuntimeException(error.v),
      identity
    )

  "def routeDistance" should "return distance of the route A-B-C" in {
    val actual = railRoadService.routeDistance(
      List(TownName('A'), TownName('B'), TownName('C'))
    )
    actual shouldEqual Right(9)
  }

  it should "return distance of the route A-D" in {
    val actual = railRoadService.routeDistance(
      List(TownName('A'), TownName('D'))
    )
    actual shouldEqual Right(5)

  }

  it should "return distance of the route A-D-C" in {
    val actual = railRoadService.routeDistance(
      List(TownName('A'), TownName('D'), TownName('C'))
    )
    actual shouldEqual Right(13)
  }

  it should "return distance of the route A-E-B-C-D" in {
    val actual = railRoadService.routeDistance(
      List(TownName('A'), TownName('E'), TownName('B'), TownName('C'), TownName('D'))
    )
    actual shouldEqual Right(22)
  }

  it should "return distance of the route A-E-D" in {
    val actual = railRoadService.routeDistance(
      List(TownName('A'), TownName('E'), TownName('D'))
    )
    actual shouldEqual Left("NO SUCH ROUTE")
  }

  "def shortestRouteDistance" should "return the length of the shortest route (in terms of distance to travel) from A to C" in {
    val actual = railRoadService.shortestRouteDistance(TownName('A'), TownName('C'))
    actual shouldEqual 9
  }

  it should "the length of the shortest route (in terms of distance to travel) from B to B" in {
    val actual = railRoadService.shortestRouteDistance(TownName('B'), TownName('B'))
    actual shouldEqual 9
  }

  "def tripCount" should "return the number of trips starting at A and ending at C with exactly 4 stops" in {
    val actual = railRoadService.tripCount(
      TownName('A'),
      TownName('C'),
      ExactlyStops(4)
    )
    actual shouldEqual 3
  }

  it should "return the number of trips starting at C and ending at C with a maximum of 3 stops" in {
    val actual = railRoadService.tripCount(
      TownName('C'),
      TownName('C'),
      MaximumStops(3)
    )
    actual shouldEqual 2
  }
  it should "return the number of different routes from C to C with a distance of less than 30" in {
    val actual = railRoadService.tripCount(
      TownName('C'),
      TownName('C'),
      DistanceLessThan(30)
    )
    actual shouldEqual 7
  }

}
