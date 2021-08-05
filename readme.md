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
   - to execute run `Application.scala`
    
2. If have `sbt` installed 
    - On terminal `sbt run`
    - For test `sbt test`
    
### How to Change input map ?
- Edit `src/main/resources/routes.conf` file

### Assumption 
- All inputs are correct
- The length of the shortest route, if non exist between two town result will be zero

### Package organization
```
com.railroad.ltqland
  - conf 
  - map (Buil rail road based on input conf)
  - services
        - distance
            RouteDistanceService 
            ShortestRouteService
        - route
            RoutesComputeService
  Application (Object with main def)
```