# Java Spring Monitoring

## Run
```shell
skaffold dev
```

## Test

[Open Zipkin UI](http://localhost:9411)

[Open Spring Admin UI](http://localhost:9999)


```shell
curl --location 'http://localhost:8090/price/iphone'
```

```shell
curl --location 'http://localhost:8080/order' \
--request POST \
--header 'Content-Type: application/json' \
--data '{
  "productIdentifier": "iphone",
  "productName": "N/A",
  "quantity": 3
}'
```