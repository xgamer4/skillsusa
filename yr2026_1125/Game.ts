import type { Base } from "./Base";
import { Board } from "./Board";
import { BotPlayer } from "./BotPlayer";
import { Player } from "./Player";
import * as globals from "./globals";
import stringLength from "string-length";

/**
 * Actions that a player is able to take for their turn. QUIT is exclusive to real players.
 */
export enum PlayerAction {
    TRAIN = "Train more units",
    ATTACK = "Attack the opponent",
    EXPAND = "Expand into a new base",
    QUIT = "Exit the game",
}

/**
 * Represents a game instance of OceanDestruction 2.
 * Keeps track of the board, players, who's turn it is, and more
 */
export class Game {
    private static numRounds = 5;
    private board: Board;
    private players: [Player, Player];
    private isPlayerOneTurn: boolean;
    private turnCount: number;
    private forceExit: boolean;

    constructor() {
        this.players = [
            new Player(this, 2, 1, Bun.color("#d11f5e", "ansi")),
            new BotPlayer(this, 2, 2, Bun.color("#45a59a", "ansi")),
        ];

        this.board = new Board(this.players);
        this.isPlayerOneTurn = false;
        this.turnCount = 0;
        this.forceExit = false;
    }

    /**
     * Get the number of bases owned by the given player
     * @param player
     * @returns
     */
    private getBasesOwnedCount(player: Player) {
        let basesOwned = 0;
        this.board.getBases().forEach((base) => {
            if (base.getOwner() !== player) {
                return;
            }
            basesOwned++;
        });
        return basesOwned;
    }

    /**
     * Clears the screen and draws the header for the game
     */
    private drawHeader() {
        const player = this.isPlayerOneTurn ? this.players[0] : this.players[1];

        // The target width of the title bar
        const titleBarWidth = Math.min(process.stdout.columns, 65);

        // the title text & currently playing player's turn text
        const title = `[${Bun.color("#1275b7", "ansi")}Ocean${Bun.color("#1512b7", "ansi")}Destruction ${globals.DEFAULT_COLOR}2]`;
        // const title = `[OceanDestruction 2]`;
        const playing = `${player.getColor()}${player.getLongName()}${globals.DEFAULT_COLOR} is playing... (turn ${this.turnCount})`;

        // Create the gap in between the title and playing text
        const gap = Math.max(titleBarWidth - stringLength(title) - stringLength(playing), 3);
        let gapString = "";
        for (let i = 0; i < gap; i++) gapString += " ";

        // Create the final header
        let header = `${title}${gapString}${playing}\n`;

        // Add line below header information
        for (let i = 0; i < titleBarWidth; i++) header += "─";

        console.clear();
        console.log(header);
    }

    /**
     * Display the dice combat GUI
     * @param attacker The attacker player
     * @param defender The defending player
     * @param attackerDie The attacking player's die
     * @param defenderDie The defending player's die
     * @param diceIndex The dice index which we are revealed up to
     */
    private drawCombatDieDisplay(
        attacker: Player,
        defender: Player,
        attackerDie: number[],
        defenderDie: number[],
        diceIndex: number,
    ) {
        let dieDisplays = [];

        // Loop through each die and add it to dieDisplays
        // If it is revealed, show it's value and the winning player's color
        for (let i = 0; i < Math.min(attackerDie.length, defenderDie.length); i++) {
            const revealed = i <= diceIndex;

            let attackerDieText = "?";
            let defenderDieText = "?";
            let color = globals.DEFAULT_COLOR;

            // Calculate the proper color and text if this die is revealed
            if (revealed) {
                const attackerDieValue = attackerDie[i] as number;
                const defenderDieValue = defenderDie[i] as number;

                let winner = attackerDieValue > defenderDieValue ? attacker : defender;
                color = revealed ? (winner.getColor() ?? color) : color;

                attackerDieText = attackerDieValue.toString();
                defenderDieText = defenderDieValue.toString();
            }

            // Add this dice row to the dice displays
            dieDisplays.push(`${color}┌───┐    ┌───┐
│ ${attackerDieText} │ vs │ ${defenderDieText} │
└───┘    └───┘${globals.DEFAULT_COLOR}`);
        }

        // Display the information needed by the combat gui
        console.clear();
        this.drawHeader();

        console.log(`${attacker.getLongName(true)} is attacking ${defender.getLongName(true)}`);

        console.log(dieDisplays.join("\n"));
    }

    /**
     * Provide the given player 1 unit per base they currently own
     * @param player
     */
    private train(player: Player) {
        player.trainUnits(this.getBasesOwnedCount(player));
    }

    /**
     * Allows the attacker to begin the combat system against the defender
     * @param attacker
     * @param defender
     */
    private async attack(attacker: Player, defender: Player) {
        let remainingAttackingUnits = attacker.getUnitCount() - 1;
        let remainingDefendingUnits = defender.getUnitCount();

        // Combat is divided into 3v2s (or less if a player doesn't have enough units)
        while (remainingAttackingUnits > 0 && remainingDefendingUnits > 0) {
            let phaseAttackingUnits = Math.min(3, remainingAttackingUnits);
            let phaseDefendingUnits = Math.min(2, remainingDefendingUnits);

            // roll die for the attackers & order the results in ascending order
            let attackingDie = [];
            for (let i = 0; i < phaseAttackingUnits; i++) {
                attackingDie.push(Math.floor(Math.random() * 6) + 1);
            }
            attackingDie.sort((a, b) => b - a);

            // roll die for the defenders & order the results in ascending order
            let defendingDie = [];
            for (let i = 0; i < phaseDefendingUnits; i++) {
                defendingDie.push(Math.floor(Math.random() * 6) + 1);
            }
            defendingDie.sort((a, b) => b - a);

            // Initial draw of the combat display
            this.drawCombatDieDisplay(attacker, defender, attackingDie, defendingDie, -1);
            await Bun.sleep(400);

            // Go through each matching die and determine who won each battle
            const battles = Math.min(phaseAttackingUnits, phaseDefendingUnits);
            for (let i = 0; i < battles; i++) {
                // Show the display and wait a moment after displaying it to continue
                this.drawCombatDieDisplay(attacker, defender, attackingDie, defendingDie, i);
                await Bun.sleep(750);

                // We know these die exist
                if ((attackingDie[i] as number) > (defendingDie[i] as number)) {
                    // Attacker unit won
                    remainingDefendingUnits--;
                    continue;
                }

                // Defending unit won
                remainingAttackingUnits--;
            }

            await Bun.sleep(400);
        }

        // Calculate the number of units lost
        const attackingUnitsLost = attacker.getUnitCount() - 1 - remainingAttackingUnits;
        const defendingUnitsLost = defender.getUnitCount() - remainingDefendingUnits;

        attacker.killUnits(attackingUnitsLost);
        defender.killUnits(defendingUnitsLost);

        // Defender loses one of their bases!
        if (defender.getUnitCount() === 0) {
            // Get all bases owned by the defender
            let defenderBases: Base[] = [];
            this.board.getBases().forEach((base) => {
                if (base.getOwner() === defender) {
                    defenderBases.push(base);
                }
            });

            if (defenderBases.length === 0) {
                // Can't take away any bases from them ...
                // We shouldn't be here
                return;
            }

            // Attacker gets the defender's base
            const baseToClaim = defenderBases[0] as Base;
            baseToClaim.setOwner(attacker);
        }
    }

    /**
     * Allow the given player to expand into an unowned base.
     * Does nothing if there are no unowned bases, although this shouldn't happen during normal gameplay.
     * @param player
     */
    private expand(player: Player) {
        let unownedBases: Base[] = [];
        this.board.getBases().forEach((base) => {
            if (base.getOwner() !== null) {
                return;
            }
            unownedBases.push(base);
        });

        // No bases to claim
        if (unownedBases.length === 0) {
            return;
        }

        // Flip the order that we claim bases in as player 2
        // Done so player 2 does not claim diagonally on their first turn
        let baseToClaim;
        if (this.isPlayerOneTurn) baseToClaim = unownedBases[0] as Base;
        else baseToClaim = unownedBases[unownedBases.length - 1] as Base;
        baseToClaim.setOwner(player);
    }

    /**
     * Step the game state, allowing for the next player to take their turn
     */
    private async step() {
        // Next player's turn
        this.isPlayerOneTurn = !this.isPlayerOneTurn;
        const player = this.isPlayerOneTurn ? this.players[0] : this.players[1];
        const other = this.isPlayerOneTurn ? this.players[1] : this.players[0];
        const bases = this.board.getBases();

        // Increment the turn counter if it is player 1's turn
        if (this.isPlayerOneTurn) {
            this.turnCount++;
        }

        this.drawHeader();

        // Display the board and game information
        console.log(this.toString());

        let existsUnownedBases = false;
        for (let i = 0; i < bases.length; i++) {
            const base = bases[i];

            if (base?.getOwner() === null) {
                existsUnownedBases = true;
                break;
            }
        }

        // only allow expansion if there exists unowned bases
        // only allow attacking if we have more than a single unit
        const action = await player.makeAction([
            { action: PlayerAction.TRAIN, available: true },
            {
                action: PlayerAction.ATTACK,
                available: player.getUnitCount() > 1,
            },
            { action: PlayerAction.EXPAND, available: existsUnownedBases },
        ]);

        switch (action) {
            case PlayerAction.TRAIN:
                this.train(player);
                break;
            case PlayerAction.ATTACK:
                await this.attack(player, other);
                break;
            case PlayerAction.EXPAND:
                this.expand(player);
                break;
            case PlayerAction.QUIT:
                this.forceExit = true;
                break;
        }
    }

    /**
     * Determine the winner of the 2 players in this game
     * @returns The winning player, or null if it's a tie
     */
    public determineWinner(): Player | null {
        const player1 = this.players[0];
        const player2 = this.players[1];

        if (player1.getUnitCount() > player2.getUnitCount()) {
            return player1;
        }
        if (player2.getUnitCount() > player1.getUnitCount()) {
            return player2;
        }

        // Unit counts equal, calculate number of bases owned
        const basesOwnedP1 = this.getBasesOwnedCount(player1);
        const basesOwnedP2 = this.getBasesOwnedCount(player2);

        if (basesOwnedP1 > basesOwnedP2) {
            return player1;
        }
        if (basesOwnedP2 > basesOwnedP1) {
            return player2;
        }

        // Everything is equal! It's a tie.
        return null;
    }

    /**
     * Begin the primary game loop
     */
    public async start() {
        // *2 to let both players play
        for (let i = 0; i < Game.numRounds * 2; i++) {
            await this.step();

            // Exit if the user selected the "QUIT" option
            if (this.forceExit) {
                console.log("Quitting ...");
                return;
            }

            // We only need to check if the other player has 0 bases and units
            // Because the player that just played can never lose after their turn
            const otherPlayer = this.isPlayerOneTurn ? this.players[1] : this.players[0];

            // Exit early if the other player has no resources
            if (otherPlayer.getUnitCount() === 0 && this.getBasesOwnedCount(otherPlayer) === 0) {
                break;
            }
        }

        const winner = this.determineWinner();

        let winningText;
        if (winner === null) winningText = "It's a tie!";
        else winningText = `${winner.getLongName(true)} wins!`;

        console.clear();
        console.log(`${winningText}\nFinal board state:\n${this.toString()}\n`);
    }

    /**
     * Display the game's information, including player unit counts, the board state, and the current turn
     */
    public toString() {
        const board = this.board.toString();
        let playerInfos: string[] = [];

        // "Player X: Y units" in player x's color
        this.players.forEach((player) => {
            playerInfos.push(`${player.getColor()}${player.getLongName()}: ${player.getUnitCount()} units`);
        });

        return `${board}\n${playerInfos.join("\n")}${globals.DEFAULT_COLOR}`;
    }
}
