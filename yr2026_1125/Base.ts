import type { Player } from "./Player";
import * as globals from "./globals";

/**
 * Represents a Base that players are able to capture in OceanDestruction 2
 */
export class Base {
    private owner: Player | null;

    constructor(owner: Player | null = null) {
        this.owner = owner;
    }

    public getOwner() {
        return this.owner;
    }

    public setOwner(owner: Player) {
        this.owner = owner;
    }

    /**
     * Export this base as a string, colored with the current player's color
     *
     * Example:
     * ┌──────┐
     * │  P1  │
     * │      │
     * └──────┘
     * (color with P1's color)
     */
    public toString() {
        const color = this.owner?.getColor() ?? Bun.color("white", "ansi");
        const label = this.owner?.getShortName() ?? "  ";

        // replace each newline with the player's color to make it compatible with how we are printing
        // out each base in Board.toString()
        return `${color}┌──────┐
│  ${label}  │
│      │
└──────┘${globals.DEFAULT_COLOR}`.replaceAll("\n", `\n${color}`);
    }
}
