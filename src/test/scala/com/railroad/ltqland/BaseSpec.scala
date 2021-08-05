package com.railroad.ltqland

import org.scalamock.scalatest.MockFactory
import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait BaseSpec extends AnyFlatSpec with Matchers with EitherValues with MockFactory
