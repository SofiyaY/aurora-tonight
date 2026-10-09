export type Chance = 'GOOD' | 'MAYBE' | 'NOT_TONIGHT';

export type City = {
  id: string;
  name: string;
};

export type Hour = {
  time: string;
  cloudPercent: number;
  chance: Chance;
};

export type Forecast = {
  city: string;
  kp: number;
  minKp: number;
  cloudPercent: number;
  chance: Chance;
  hours: Hour[];
};

export const chanceText: Record<Chance, string> = {
  GOOD: 'Good chance',
  MAYBE: 'Maybe',
  NOT_TONIGHT: 'Not tonight',
};
