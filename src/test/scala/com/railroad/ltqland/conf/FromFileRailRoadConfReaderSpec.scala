package com.railroad.ltqland.conf

import com.railroad.ltqland.BaseSpec

class FromFileRailRoadConfReaderSpec extends BaseSpec {

  "def read" should "read routes from given file path" in {
    val filePath     = "src/test/resources/routes-test.conf"
    val actualRoutes = FromFileRailRoadConfReader(filePath).read.value.strRoutes

    val expected = Set("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
    actualRoutes shouldEqual expected

  }

  it should "fail for invalid file path" in {
    val filePath = "invalidPath"
    assert(FromFileRailRoadConfReader(filePath).read.isLeft)
  }

}
