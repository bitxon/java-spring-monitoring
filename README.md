# Java Spring Monitoring

## Run

### In Kubernetes (skaffold)
1. Start cluster
    - `minikube start`
2. Run apps:
   - `skaffold dev`
3. Run load-test:
   - `artillery run artillery-load-test.yml`

### In Docker
1. Start containers
    - `docker-compose up -d`
2. Run apps:
    - `mvn -pl price-service -am spring-boot:run`
    - `mvn -pl order-service -am spring-boot:run`
    - `mvn -pl spring-boot-admin -am spring-boot:run`
3. Run load-test:
   - `artillery run artillery-load-test.yml`

---
## Test

[Open - Zipkin](http://localhost:9411)\
[Open - Spring Admin](http://localhost:9999)\
[Open - Prometheus](http://localhost:9090/targets)\
[Open - Grafana](http://localhost:3000/d/d3092176-b514-4046-90e9-61554c809dec)


```shell
curl --location 'http://localhost:8090/price/iphone' \
--header 'x-request-id: A8j24hu81b33' \
--header 'x-application-id: Web'
```

```shell
curl --location 'http://localhost:8080/order' \
--request POST \
--header 'Content-Type: application/json' \
--header 'x-request-id: Ju439a8o1yr4' \
--header 'x-application-id: Web' \
--data '{
  "productIdentifier": "iphone",
  "productName": "N/A",
  "quantity": 3
}'
```

---
## Useful Links
[Spring Boot Grafana Dashboards](https://grafana.com/grafana/dashboards/?search=spring+boot)\
[Spring Boot 3 Dashboard file for Grafana](https://grafana.com/grafana/dashboards/19004-spring-boot-statistics/)\
[Get Artillery](https://www.artillery.io/docs/get-started/get-artillery)
