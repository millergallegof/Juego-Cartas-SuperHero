import { Bajara } from './Ibaraja';
export interface Jugador{
    id: string;
    nickName: string;
    puntos: number;
    baraja: Bajara | null;
    estado: boolean;
}