# Java Spring Monitoring

## Run
```shell
skaffold dev
```

## Test

[Open Zipkin UI](http://localhost:9411)

[Open Spring Admin UI](http://localhost:9999)


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