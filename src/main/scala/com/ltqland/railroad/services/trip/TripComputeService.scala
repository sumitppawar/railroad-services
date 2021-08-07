package com.ltqland.railroad.services.trip

import com.ltqland.railroad.domain.TownName

trait TripComputeService {

  def compute(
    from: TownName,
    to: TownName,
    filter: (String, Int) => Boolean,
    stopCriteria: (String, Int) => Boolean
  ): Int
}
