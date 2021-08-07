package com.ltqland.railroad.conf

import com.ltqland.railroad.BaseSpec
import com.ltqland.railroad.domain.{ Road, TownName }

class FileConfReaderSpec extends BaseSpec {

  "def read" should "read routes from given file path" in {
    val filePath     = "src/test/resources/routes-test.conf"
    val actualRoutes = FileConfReader(filePath).read.value.roads

    val expected = Set(
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
    actualRoutes shouldEqual expected

  }

  it should "fail for invalid file path" in {
    val filePath = "invalidPath"
    assert(FileConfReader(filePath).read.isLeft)
  }

}
