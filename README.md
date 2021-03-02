# How to

Pre req:

* `docker`
* `docker-compose`
* `JDK-11`

### To start dev and test database

```shell
docker-compose up
```

### To stop dev and test database

```shell
docker-compose down
```

### To test

```shell
# For linux/mac
./mvnw test

# For windows
mvnw.cmd test
```

### To run

```shell
# For linux/mac
./mvnw spring-boot:run

# For windows
mvnw.cmd spring-boot:run
```

---

### Example request

```shell
curl --request POST \
  --url http://localhost:8080/api/user \
  --header 'Content-Type: application/json' \
  --data '{
	"firstName": "Tim",
	"lastName": "Page",
	"phone": "+378 85 34",
	"email": "james@bloom.cok",
	"addressLine1": "Brook Street Lane 67",
	"addressLine2": null,
	"city": "Berlin",
	"state": "Brandenburg",
	"zip": "36413",
	"countryCode": "DE"
}'
```