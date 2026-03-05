import { Base } from "./Base";
import type { Player } from "./Player";
import * as globals from "./globals";

/**
 * Represents the board state for a game of OceanDestruction 2
 */
export class Board {
    private bases: [Base, Base, Base, Base];

    constructor(players: [Player, Player]) {
        this.bases = [new Base(players[0]), new Base(players[1]), new Base(), new Base()];
    }

    public getBases() {
        return this.bases;
    }

    /**
     * Visualize the board, displaying all bases
     */
    public toString() {
        const basesPerRow = 2;
        let finalString = "";

        for (let i = 0; i < this.bases.length; i += basesPerRow) {
            // a list containing a list of each line of a base.
            // Used so we can show multiple bases on the same horizontal line.
            const baseStrings: string[][] = [];

            // Add each base's lines to a new list in baseStrings
            for (let t = 0; t < basesPerRow; t++) {
                const thisBase = this.bases[i + t];
                if (thisBase === undefined) break;
                baseStrings.push(thisBase.toString().split("\n"));
            }

            if (baseStrings[0] === undefined) {
                continue;
            }

            // Loop through each row, and grab each base's line for that row and add it to the final output
            // We assume that each base.toString() has the same number of rows
            for (let row = 0; row < baseStrings[0].length; row++) {
                for (let baseIndex = 0; baseIndex < baseStrings.length; baseIndex++) {
                    const thisBase = baseStrings[baseIndex];
                    // this shouldn't happen
                    if (thisBase === undefined) break;

                    const thisBaseLine = thisBase[row];
                    // this also shouldn't happen
                    if (thisBaseLine === undefined) break;

                    finalString += thisBaseLine;
                }

                finalString += "\n";
            }
        }

        return finalString;
    }
}
