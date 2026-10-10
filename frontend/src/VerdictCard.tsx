import type { Forecast } from './types';
import { chanceText } from './types';

export default function VerdictCard({ forecast }: { forecast: Forecast }) {
  return (
    <section className={'card verdict ' + forecast.chance.toLowerCase()}>
      <p className="where">{forecast.city}, right now</p>
      <h2>{chanceText[forecast.chance]}</h2>

      <div className="stats">
        <div className="stat">
          <span className="label">Aurora activity</span>
          <span className="value">Kp {forecast.kp.toFixed(1)}</span>
        </div>
        <div className="stat">
          <span className="label">Needed here</span>
          <span className="value">Kp {forecast.minKp}</span>
        </div>
        <div className="stat">
          <span className="label">Clouds</span>
          <span className="value">{Math.round(forecast.cloudPercent)}%</span>
        </div>
      </div>
    </section>
  );
}
