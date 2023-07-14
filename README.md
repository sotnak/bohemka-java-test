### Java test

Simple web application, which contains:
- Persistent data which can be modified and read.
- HTTP REST API for accessing the data by clients.
- Expiration of old data.
---
REST API uses JSON format to transfer data and provides following actions:
- Create Item: POST /item
- Get Item: GET /item/{id}
- Search Items: GET /item with URL params:
  - tags: specify one or more tags expecting that server will return only Items which are tagged by these tags.
  - value: allows searching for Items by value.
  - operator: specifies what operator to use in combination with value (=,>,<).
  - orderBy: ordering search result by specified attribute.
  - orderType: descendant or ascendant.
  - limit: limit of number of Items in response.
  - page: pagination, tied to limit.
- Update Item: PUT /item/{id}
- Delete Item: DELETE /item/{id}
---
Technologies used:
- Application language: Java
- Application framework: Spring
- Database: MongoDB (persistent data), Redis (caching)
- Application distribution / deployment: Docker
---
REST API can be build by ```mvn clean install```.
Docker image is build using already existing API build in target folder.
API, persistent data and caching database can be started by ```docker compose up -d```.
API image supports following environmental parameters:
- SPRING_DATA_MONGODB_URI: URI to MongoDB (e.g. mongodb://mongo:27017/).
- SPRING_DATA_MONGODB_DATABASE: database of MongoDB.
- SPRING_DATA_REDIS_HOST: host of Redis (e.g. localhost).
- SPRING_DATA_REDIS_PORT: port of Redis (e.g. 6379).
- MONGO_EXPIRE: after how many seconds, will an Item expire in MongoDB.
- REDIS_TTL: time-to-live of a cache record (in seconds).
---
For testing purposes, test.http files was created.
It contains some basic operations.