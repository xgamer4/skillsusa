import { Game, PlayerAction } from "./Game";
import { playerSelect } from "./utils";
import * as globals from "./globals";

/**
 * Represents a player in the game of OceanDestruction 2
 */
export class Player {
    private unitCount: number;
    /** This player's number relative to the current game. Used in displaying the player's name. */
    private playerNumber: number;
    private color: string | null;
    protected isBot: boolean;
    protected game: Game;

    constructor(game: Game, unitCount: number, playerName: number, color: string | null) {
        this.game = game;
        this.unitCount = unitCount;
        this.playerNumber = playerName;
        this.color = color;
        this.isBot = false;
    }

    public getColor() {
        return this.color;
    }

    public getUnitCount() {
        return this.unitCount;
    }

    /**
     * Display this player as "PX", where X is this player's playerNumber
     * @returns
     */
    public getShortName() {
        return `P${this.playerNumber}`;
    }

    /**
     * Display this player as "Player X", where X is this player's playerNumber
     * @returns
     */
    public getLongName(color: boolean = false) {
        return `${color ? this.color : ""}Player ${this.playerNumber}${color ? globals.DEFAULT_COLOR : ""}`;
    }

    /**
     * Add the number of newUnits to the total number of units this player owns
     * @param newUnits
     */
    public trainUnits(newUnits: number) {
        this.unitCount += newUnits;
    }

    /**
     * Removes the given number of units from the total number of units this player owns
     * @param lostUnits
     */
    public killUnits(lostUnits: number) {
        this.unitCount -= lostUnits;
    }

    /**
     * Allows the user to determine the action that they would like to make
     * @param actions The possible actions that should be chosen from
     * @returns The action that is being made
     */
    public async makeAction(actions: { action: PlayerAction; available: boolean }[]): Promise<PlayerAction> {
        // Add an additional "Exit game" option since we know this is the user (additional bot logic is in BotPlayer.ts)
        actions.push({ action: PlayerAction.QUIT, available: true });

        const userChoice = playerSelect(
            "\nMake your move!",
            actions.map((value) => {
                return { text: value.action, available: value.available };
            }),
        );

        // we know that userChoice will be in bounds
        return (actions[userChoice] as { action: PlayerAction }).action;
    }
}
