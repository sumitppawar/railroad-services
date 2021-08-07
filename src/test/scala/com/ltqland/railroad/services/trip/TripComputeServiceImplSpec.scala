package com.ltqland.railroad.services.trip

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.domain.TownName

class TripComputeServiceImplSpec extends BaseSpec {

  "def compute" should "return the number of trips starting at C and ending at C with a maximum of 3 stops" in {
    val service = TripComputeServiceImpl(exampleMap)
    service.compute(
      TownName('C'),
      TownName('C'),
      (path, _) => path.length <= 3,
      (path, _) => path.length > 3
    )
  }

  it should "the number of different routes from C to C with a distance of less than 30" in {
    val service = TripComputeServiceImpl(exampleMap)
    service.compute(
      TownName('C'),
      TownName('C'),
      (_, distance) => distance < 30,
      (_, distance) => distance > 30
    )
  }

  it should "return the number of trips starting at A and ending at C with exactly 4 stops" in {
    val service = TripComputeServiceImpl(exampleMap)
    service.compute(
      TownName('C'),
      TownName('C'),
      (path, _) => path.length == 4,
      (path, _) => path.length > 4
    )
  }

}
