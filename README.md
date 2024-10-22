# Scale-Android
An android application that shows the current weather

This application fetches the current weather in the current location. It is requesting an API call to `OpenWeather` service.
To change the `OpenApi` public API key: Go to **Data > Core > Src > Main > Res > Values** directory. Add the new API key in the `string` field of "open_weather_api_key".

This application consists of 3 main screens, which are Login Screen, Current Weather Screen, and Recorded Weather Screen. It also connects to a very basic server and database in Vercel for user authentication, and storing of weather logs.

**Login Screen** <br/>
In this screen, if user has an existing account, user can login using his/her credentials. If not, then user can create account in sign up page.<br/>

<p align="center">
      <img src="https://github.com/user-attachments/assets/f8001fd7-fc51-455c-8db3-20c404d58003" width=30%>
      <img src="https://github.com/user-attachments/assets/49cc6bdb-63e3-4d86-a4cd-d3846f900a39" width=30%>
</p>

**Current Weather Screen** <br/>
In this screen, the app fetches the current weather in the area. Since, the device's location is needed, a `location` permission should be granted by the user. The gradient background color of the screen changes depending on the time it was fetched.

<p align="center">
      <img src="https://github.com/user-attachments/assets/f51eac98-1f16-4905-8438-dabad5ed5b50" width=30%>
      <img src="https://github.com/user-attachments/assets/d77a3125-de62-413c-b650-f0c957efb9ad" width=30%>
</p>

**Recorded Weather Screen** <br/>
The app automatically fetches the current weather when user opens the app. The data can be shown at the 2nd tab of the home page.

<p align="center">
      <img src="https://github.com/user-attachments/assets/38beb28a-0875-4c56-ab82-70ca6aad0ff9" width=30%>
</p>
