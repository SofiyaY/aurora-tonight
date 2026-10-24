# Aurora Tonight

A small web app that tells you if you can see the northern lights tonight in a Norwegian city.

Pick a city and the app shows Good chance, Maybe or Not tonight, the numbers behind
the answer, and the chance for each of the next eight hours.

## How it works

The backend gets two things:

- the aurora activity right now (the Kp index, 0 to 9) from NOAA
- the cloud forecast for the city from MET Norway, the same data as Yr

Each city needs a certain Kp to see the aurora. Tromsø needs about Kp 0, Oslo about Kp 4.
These values come from SpaceWeatherLive.

The answer is decided by a few rules:

- more than 70% clouds: Not tonight
- Kp at least 1 above what the city needs and 40% clouds or less: Good chance
- Kp at least what the city needs: Maybe
- otherwise: Not tonight

## Tech

- Backend: Java 21, Spring Boot 3, JUnit
- Frontend: React, TypeScript, Vite, CSS

## Run it

You need Java 21, Maven and Node.js.

Backend, on port 8080:

```bash
cd backend
mvn spring-boot:run
```

Frontend, on port 5200, in a new terminal:

```bash
cd frontend
npm install
npm run dev
```

Then open http://localhost:5200.

Run the tests with `mvn test` in the backend folder.

## Credits

- Aurora data: [NOAA Space Weather Prediction Center](https://www.swpc.noaa.gov/)
- Weather data: [MET Norway](https://api.met.no/) (CC BY 4.0)
- Kp values for each city: [SpaceWeatherLive](https://www.spaceweatherlive.com/en/help/the-kp-index.html)
- Background photo: Simo Räsänen (Ximonic), [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/), via
  [Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Northern_lights_curtains_and_beams_over_Nordmo_in_Bjerkvik,_Narvik,_Nordland,_Norway,_2023_September_-_3.jpg)
