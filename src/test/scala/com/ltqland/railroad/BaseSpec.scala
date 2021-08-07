package com.ltqland.railroad

import com.ltqland.railroad.domain.{ Road, TownName }
import com.ltqland.railroad.network.NetworkBuilder
import org.scalamock.scalatest.MockFactory
import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait BaseSpec extends AnyFlatSpec with Matchers with EitherValues with MockFactory {

  protected val exampleMap = NetworkBuilder(
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
}
