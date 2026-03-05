import { Action, Game } from "./Game";

/**
 * Interface for classes which provide player input into the game. Used for both human and AI players.
 */
export interface PlayerInterface {
    name: string;
    possesive?: string;
    promptAction: (game: Game) => Promise<Action>;
}