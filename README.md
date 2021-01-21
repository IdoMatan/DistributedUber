# Distributed Uber

Final Project in Technion Course 236351 - Distributed Systems Department of Computer Science

---
## Table of Contents

- [Dependencies](#dependencies)
- [Installation](#installation)
- [APIs](#APIs)
- [Results](#results)

---
## Dependencies
- Zookeeper
- Spring
- Gradle

---
## Installation
- Clone this repo
- Run Zookeeper service on port 2181
- in `node/src/main/resources/application.properties` set the REST and gRPC ports and a shard id from [A,B,C,D]
- The cities each shard handles are currently hardcoded (we'll add this a config file in the future)
- Run `Node/src/main/java/host/Application.java`
- Start sending requests!

---
## APIs:
- `POST <any_participating_server_uri>/new_ride`
```
body:
{
"first_name": "John",
 "last_name": "Smith",
 "phone_number": "054664455",
 "origin": "cityA",
"destination": "cityB",
"vacancies": 2,
"departure_date": "2020-12-01"
}
```
- `POST <any_participating_server_uri>/book/ride/single`
```
body:
{
"first_name": "Mark",
"last_name": "Walt",
"origin": "cityA",
"destination": "cityB",
"departure_date": "2020-12-01"
}
```

- `POST <any_participating_server_uri>/book/ride/path_planning`
```
body:
{
"first_name": "Jenny",
"last_name": "Walt",
"origin": ["cityA", “cityB”, “cityC”]
"destination": ["cityB",”cityC”, “cityD”]
"departure_date": ["2020-12-01”, "2020-12-01", "2020-12-02"]
}
```
#### Cities can be: [cityA, cityB, cityC, cityD, cityE, cityF, cityG, cityH]
---
## Results

![book_ride](https://github.com/IdoMatan/DistributedUber/blob/master/Figures/book_ride%204%20shards.png)
- Example - Time to add a new ride to the system (8 cities)

---