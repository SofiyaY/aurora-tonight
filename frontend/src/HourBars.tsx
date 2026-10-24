import type { Hour } from './types';
import { chanceText } from './types';

export default function HourBars({ hours }: { hours: Hour[] }) {
  return (
    <section className="card">
      <h3>Next hours</h3>
      <div className="bars">
        {hours.map(hour => {
          const clearSky = 100 - hour.cloudPercent;
          const time = new Date(hour.time).toLocaleTimeString('en-GB', { hour: '2-digit', minute: '2-digit' });
          return (
            <div key={hour.time} className="bar-column" title={`${time}: ${chanceText[hour.chance]}`}>
              <span className="bar-label">{Math.round(clearSky)}%</span>
              <div className="bar-track">
                <div className={'bar ' + hour.chance.toLowerCase()} style={{ height: `${Math.max(clearSky, 4)}%` }} />
              </div>
              <span className="bar-time">{time}</span>
            </div>
          );
        })}
      </div>
      <p className="hint">% clear sky each hour. Taller bar = fewer clouds</p>
    </section>
  );
}
