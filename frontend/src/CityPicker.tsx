import type { City } from './types';

type Props = {
  cities: City[];
  selected: string;
  onSelect: (id: string) => void;
};

export default function CityPicker({ cities, selected, onSelect }: Props) {
  return (
    <nav className="cities">
      {cities.map(city => (
        <button
          key={city.id}
          className={city.id === selected ? 'city active' : 'city'}
          onClick={() => onSelect(city.id)}
        >
          {city.name}
        </button>
      ))}
    </nav>
  );
}
