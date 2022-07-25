import { Tarjeta } from './Itarjetas';

export interface Tablero {
    id: string | null;
    apuesta: Map<string, Tarjeta> | null;
    ganadorId: string;
    tiempo: number;
}