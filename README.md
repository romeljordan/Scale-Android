# Scale-Android
An android application that shows the current weather

This application consists of 3 screens: LoginScreen, WeatherScreen, and RecordsScreen. It also connects to a very basic server and database in Vercel for user authentication, and storing of weather logs.

This application fetches the current weather in the current location. It is requesting an API call to `OpenWeather` service.
To change the `OpenApi` public API key: Go to Data > Core > Src > Main > Res > Values directory. Add the new API key in the `string` field of "open_weather_api_key".
