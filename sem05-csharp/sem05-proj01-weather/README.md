# Console Weather App

Example query:

```
select * from weather.forecast where woeid in (select woeid from geo.places(1) where text="vienna") and u="c"
```

Endpoint:

```
https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22vienna%22)%20and%20u%3D%22c%22&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
```

