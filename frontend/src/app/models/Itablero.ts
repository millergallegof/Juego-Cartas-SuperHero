import { Tarjeta } from './Itarjetas';

export interface Tablero {
    id: string;
    apuesta: Map<string, Tarjeta>;
    ganadorId: string;
    tiempo: number;
}