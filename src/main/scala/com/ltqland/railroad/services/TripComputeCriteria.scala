package com.ltqland.railroad.services

sealed trait TripComputeCriteria

case class MaximumStops(v: Int)     extends TripComputeCriteria
case class ExactlyStops(v: Int)     extends TripComputeCriteria
case class DistanceLessThan(v: Int) extends TripComputeCriteria
