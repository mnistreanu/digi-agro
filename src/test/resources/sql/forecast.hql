SELECT 

    forecast.id,
    forecast.code,
    forecast.message,
    forecast.cnt,
--    private List<ForecastHourItem> forecastItems = new ArrayList<>();
    forecast.cityId,
    forecast.name,
    forecast.countryCode,
    forecast.lon,
    forecast.lat


FROM WeatherForecastHour forecast
-- WHERE forecast.dayTimestamp <= 1535749200
-- GROUP BY forecast.openweatherId, forecast.parcelId, forecast.dayTimestamp