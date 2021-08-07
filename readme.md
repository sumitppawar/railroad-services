Railroad-Services
--

### Tech/Libs
1. `scala 2.13.6`
    - `scalaTest`
2. `sbt.version = 1.5.5`
3. `openjdk 8`
4. `slf4j + logback`
5. `sbt-scalafmt`


### How to run ?
1. Import in `IntelliJ` as `sbt` project
   - need to have `Scala Plugin`
   - to execute given input test `RailRoadServiceSpec.scala`
    
2. If have `sbt` installed 
    - For all test `sbt test`
    
### How to Change input map ?
- Edit `src/main/resources/routes-test.conf` file

### Assumption 
- All inputs are correct (Map & Test input)
- The length of the shortest route, if non exist between two town result will be zero

### Package organization
```
com.railroad.ltqland
  - conf 
  - network (Buil rail road based on input conf)
  - domain 
  - services
        - distance
            RouteDistanceService 
            ShortestRouteService
        - trip
            TripComputeService
        RailRoadService    
  Application (Object with main def and RailRoadService example ) 
```