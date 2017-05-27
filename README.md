# TgvMaxBooking
Selenium based batch to book train with TGV MAX promo

## Build
* Add selenium-server-standalone-3.4.0.jar
add **selenium-server-standalone-3.4.0.jar** into **libs** folder
The standalone jar should be the first declared in classpath

* Maven build
```sh
mvn package -P DEV
```

* Maven build - fat jar
```sh
mvn package -P BUILD
```

## Run
```sh
./devstart.sh <DEPARTURE_CITY> <DESTINATION_CITY> <DATE AS DD/MM/YYYY> <HOUR> <MINUTE>
```
Use **prodstart.sh** to run fat jar.

## Develop
add Customer into `com.rambaud.train.train_booking.model.Customer`

## License
Licensed under [MIT](https://github.com/JeanPascalR/TgvMaxBooking/blob/master/LICENSE)