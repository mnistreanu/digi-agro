SELECT snapshot.openweatherId AS openweatherId, 
    snapshot.dayTimestamp AS dayTimestamp, 
    snapshot.parcelId AS parcelId, 
    MIN(snapshot.tempMin) AS tempMin, 
    MAX(snapshot.tempMax) AS tempMax, 
    MIN(CASE 
        WHEN HOUR(dt) BETWEEN 6 AND 9 THEN snapshot.temp ELSE 0 
    END) AS morn,
    MAX(CASE 
        WHEN HOUR(dt) BETWEEN 9 AND 18 THEN snapshot.temp ELSE 0 
    END) AS day,
    MIN(CASE 
        WHEN HOUR(dt) BETWEEN 18 AND 21 THEN snapshot.temp ELSE 0 
    END) AS evn,
    MIN(CASE 
        WHEN HOUR(dt) > 9 OR HOUR(dt) < 6 THEN snapshot.temp ELSE 0 
    END) AS night,
    MAX(snapshot.pressure) AS pressure,
    MAX(snapshot.humidity) AS humidity,
    MAX(snapshot.humidityAir) AS humidityAir,
    MAX(snapshot.humiditySoil) AS humiditySoil,
    MAX(snapshot.weatherId) AS weatherId,
    MAX(snapshot.main) AS main,
    MAX(snapshot.description) AS description,
    MAX(snapshot.speed) AS speed,
    MAX(snapshot.deg) AS deg,
    MAX(snapshot.clouds) AS clouds,
    MAX(snapshot.rain3h) AS rain3h
FROM WeatherSnapshot snapshot
WHERE snapshot.dayTimestamp <= 1535749200
GROUP BY snapshot.openweatherId, snapshot.parcelId, snapshot.dayTimestamp