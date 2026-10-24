import { useEffect, useState } from 'react';
import type { City, Forecast } from './types';
import CityPicker from './CityPicker';
import VerdictCard from './VerdictCard';
import HourBars from './HourBars';

export default function App() {
  const [cities, setCities] = useState<City[]>([]);
  const [cityId, setCityId] = useState('tromso');
  const [forecast, setForecast] = useState<Forecast | null>(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    fetch('/api/cities')
      .then(res => res.json())
      .then(setCities)
      .catch(() => setError(true));
  }, []);

  useEffect(() => {
    setForecast(null);
    setError(false);
    fetch(`/api/forecast/${cityId}`)
      .then(res => res.json())
      .then(setForecast)
      .catch(() => setError(true));
  }, [cityId]);

  return (
    <div className="page">
      {/* coloured glow behind everything, set by the answer */}
      <div className={'glow ' + (forecast ? forecast.chance.toLowerCase() : '')} />

      <header className="top">
        <h1>Aurora Tonight</h1>
        <p>Can you see the northern lights where you are?</p>
      </header>

      <CityPicker cities={cities} selected={cityId} onSelect={setCityId} />

      {error && <div className="card">Could not reach the backend. Is it running?</div>}
      {!error && !forecast && <div className="card loading">Checking the sky…</div>}
      {forecast && (
        <>
          <VerdictCard forecast={forecast} />
          <HourBars hours={forecast.hours} />
        </>
      )}

      <footer className="credit">
        Aurora data from NOAA, weather from MET Norway (Yr). Photo: Simo Räsänen (Ximonic),{' '}
        <a href="https://creativecommons.org/licenses/by-sa/4.0/" target="_blank" rel="noopener">CC BY-SA 4.0</a>
      </footer>
    </div>
  );
}
