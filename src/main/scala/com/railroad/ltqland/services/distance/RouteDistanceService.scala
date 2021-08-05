package com.railroad.ltqland.services.distance

trait RouteDistanceService {
  def compute(route: String): Either[String, Int]
}
