import { ARG_BASES_TOTAL, ARG_NO_WAIT, ARG_ROUNDS_TOTAL, ARG_UNITS_START } from "./arguments";
import { CLI } from "./CLI";
import { boxed, printBattle, printDice } from "./lib";
import { PlayerInterface } from "./PlayerInterface";

enum Player {
    ONE,
    TWO
}
const playerColors = {
    [Player.ONE]: 'blue',
    [Player.TWO]: 'red',
}

export enum Action {
    TRAIN = 'TRAIN',
    ATTACK = 'ATTACK',
    EXPAND = 'EXPAND',
    QUIT = 'QUIT',
}

const STARTING_BASES = [Player.ONE, null, null, Player.TWO];
const STARTING_PLAYER = Player.ONE;
const STARTING_UNITS = ARG_UNITS_START || 2;
const TOTAL_ROUNDS = ARG_ROUNDS_TOTAL || 5;
const OUTPUT_WIDTH = 52;

if (ARG_BASES_TOTAL !== 4) {
    STARTING_BASES.length = 0;
    for (let i = 0; i < ARG_BASES_TOTAL; i++) {
        if (i === 0) {
            STARTING_BASES.push(Player.ONE);
        } else if (i === ARG_BASES_TOTAL - 1) {
            STARTING_BASES.push(Player.TWO);
        } else {
            STARTING_BASES.push(null);
        }
    }
}

export class Game {
    bases = STARTING_BASES;
    round = 0;
    totalRounds = TOTAL_ROUNDS;
    phase = STARTING_PLAYER;
    units = {
        [Player.ONE]: STARTING_UNITS,
        [Player.TWO]: STARTING_UNITS,
    }

    get isLeftPlayersTurn() {
        return this.phase === Player.ONE;
    }

    actions = {
        [Action.TRAIN]: this.train.bind(this),
        [Action.ATTACK]: this.attack.bind(this),
        [Action.EXPAND]: this.expand.bind(this),
        [Action.QUIT]: () => process.exit(0),
    }

    constructor(private player1: PlayerInterface, private player2: PlayerInterface) {
        this.promptAction();

    }

    private async wait(ms: number) {
        if (ARG_NO_WAIT) return;
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    private getPlayerName(player: Player, possesive = false) {
        const playerObject = player === Player.ONE ? this.player1 : this.player2;
        const name = playerObject.name.toUpperCase();
        const possesiveName = playerObject.possesive ? playerObject.possesive.toUpperCase() : name + '\'S';
        return CLI.color((possesive ? ` ${possesiveName}` : ` ${name}`), playerColors[player]);
    }
    /**
     * Claim the next closest unclaimed base for the current player.
     */
    private expand(force: boolean = false) {
        const furthestOwnedBase = this.isLeftPlayersTurn ? this.bases.lastIndexOf(this.phase) : this.bases.indexOf(this.phase);
        // check the base to the right if user, left if opponent
        const nextBaseIndex = this.isLeftPlayersTurn ? furthestOwnedBase + 1 : furthestOwnedBase - 1;
        if ((nextBaseIndex >= 0 && nextBaseIndex < this.bases.length && this.bases[nextBaseIndex] === null) || force) {
            this.claimBase(this.phase, nextBaseIndex);
        } else {
            console.log(`Nothing happened...`)
        }
    }
    /**
     * Train, adds one unit per base owned by the current player.
     */
    private train() {
        const ownedBases = this.bases.filter(base => base === this.phase).length;
        this.units[this.phase] += ownedBases;
    }

    /**
     * Attack, 
     * Attacker Units = Total units - 1 (must leave at least one unit behind)
     * Defender Units = Total units
     * 
     * For each pair of attacking and defending units, roll a six-sided die. If the attacker's roll is higher, the defender loses a unit. If the defender's roll is higher or equal, the attacker loses a unit. If the defender loses all their units, the attacker takes one of their bases (the one closest to them) and moves one unit into it. If the attacker has less than 2 units left after the battle, they cannot attack again until they train more units.
    */
    private async attack(): Promise<void> {
        // attacker must have at minium 2 units to attack
        if (this.units[this.phase] < 2) {
            console.log(`${CLI.color(this.getPlayerName(this.phase, true), playerColors[this.phase])} does not have enough units to attack. Must have at least 2 units.`);
            return;
        }
        const attacker = this.phase;
        const defender = attacker === Player.ONE ? Player.TWO : Player.ONE;

        const attackingUnits = this.units[attacker] - 1;
        const defendingUnits = this.units[defender];

        console.log(`${CLI.color("Attacker", playerColors[attacker])} rolling dice for ${attackingUnits} units...`);
        await this.wait(1000);
        const attackingRolls = Array(attackingUnits).fill(0).map(() => this.rollDie()).sort((a, b) => b - a);
        printDice(attackingRolls);

        console.log(`${CLI.color("Defender", playerColors[defender])} rolling dice for ${defendingUnits} units...`);
        await this.wait(1000);
        const defendingRolls = Array(defendingUnits).fill(0).map(() => this.rollDie()).sort((a, b) => b - a);
        printDice(defendingRolls);

        // each pair of rolls, lets show the win and less


        const comparisons = Math.min(attackingRolls.length, defendingRolls.length);
        let attackerLosses = 0;
        let defenderLosses = 0;
        console.log('-'.repeat(OUTPUT_WIDTH));
        for (let i = 0; i < comparisons; i++) {
            console.log(`Battle ${i + 1}:`);
            printBattle(attackingRolls[i]!, defendingRolls[i]!);
            if (attackingRolls[i]! > defendingRolls[i]!) {
                defenderLosses++;
                console.log(`${CLI.color("Attacker", playerColors[attacker])} wins this battle`);
            } else {
                attackerLosses++;
                console.log(`${CLI.color("Defender", playerColors[defender])} wins this battle`);
            }
            await this.wait(1000);
        }
        console.log('-'.repeat(OUTPUT_WIDTH));
        this.units[attacker] = Math.max(0, this.units[attacker] - attackerLosses);
        this.units[defender] = Math.max(0, this.units[defender] - defenderLosses);

        console.log(boxed(`${CLI.color(this.getPlayerName(attacker), playerColors[attacker])} lost ${attackerLosses} units.\n${CLI.color(this.getPlayerName(defender), playerColors[defender])} lost ${defenderLosses} units.`))

        if (this.units[defender] === 0 || this.units[attacker] < 2) {
            // if defender lost all units, attacker takes the base closest to them
            if (this.units[defender] === 0) {
                this.expand(true);
            }
        } else {
            console.log(`${CLI.color("No one", 'yellow')} takes a base, but the battle continues...`);
            await this.wait(2000);
            return await this.attack()
        }
    }

    /**
     * Helper function to simulate rolling a six-sided die.
     * @returns 
     */
    private rollDie() {
        return Math.floor(Math.random() * 6) + 1;
    }

    /**
     * Helper function to claim a base for a player and print out a message about it.
     * @param player 
     * @param index 
     */
    private claimBase(player: Player, index: number) {
        this.bases[index] = player;
        console.log(`${CLI.color(this.getPlayerName(player), playerColors[player])} got a base!`);
    }

    /**
     * Prompts the current player for an action, then executes that action and advances to the next phase.
     */
    private async promptAction() {
        const currentPlayer = this.isLeftPlayersTurn ? this.player1 : this.player2;
        const action = await currentPlayer.promptAction(this);
        const actionFunc = this.actions[action];
        if (actionFunc) {
            console.log(`${CLI.color(this.getPlayerName(this.phase), playerColors[this.phase])} chose to ${action}`);
            await actionFunc();
            console.log(this.toString());
            await this.wait(1000);
            this.nextPhase();
        }
    }

    /**
     * Checks if the game should continue to the next phase, or if a player has won. If the game continues, it switches the current player and prompts them for an action.
     * Also advances the round number after each back and forth between the two players.
     */
    private nextPhase() {
        // check win condition, if a player has no bases and no units, they lose
        if (this.round >= this.totalRounds) {
            // player with more units wins, tie-break with more bases, if still tied, draw
            const playerOneUnits = this.units[Player.ONE];
            const playerTwoUnits = this.units[Player.TWO];
            if (playerOneUnits > playerTwoUnits) {
                console.log(`${CLI.color(this.getPlayerName(Player.ONE), playerColors[Player.ONE])} wins by having more units!`);
                process.exit(0);
            } else if (playerTwoUnits > playerOneUnits) {
                console.log(`${CLI.color(this.getPlayerName(Player.TWO), playerColors[Player.TWO])} wins by having more units!`);
                process.exit(0);
            } else {
                const playerOneBases = this.bases.filter(base => base === Player.ONE).length;
                const playerTwoBases = this.bases.filter(base => base === Player.TWO).length;
                if (playerOneBases > playerTwoBases) {
                    console.log(`${CLI.color(this.getPlayerName(Player.ONE), playerColors[Player.ONE])} wins by having more bases as a tiebreaker!`);
                } else if (playerTwoBases > playerOneBases) {
                    console.log(`${CLI.color(this.getPlayerName(Player.TWO), playerColors[Player.TWO])} wins by having more bases as a tiebreaker!`);
                }
                process.exit(0);
            }
        }
        const playerOneBases = this.bases.filter(base => base === Player.ONE).length;
        const playerTwoBases = this.bases.filter(base => base === Player.TWO).length;
        if (playerOneBases === 0 && this.units[Player.ONE] === 0) {
            console.log(`${CLI.color(this.getPlayerName(Player.ONE), playerColors[Player.ONE])} has been defeated!`);
            process.exit(0);
        } else if (playerTwoBases === 0 && this.units[Player.TWO] === 0) {
            console.log(`${CLI.color(this.getPlayerName(Player.TWO), playerColors[Player.TWO])} has been defeated!`);
            process.exit(0);
        }
        if (this.phase === Player.TWO) {
            this.round++;
        }
        this.phase = this.phase === Player.ONE ? Player.TWO : Player.ONE;
        // prompt for action,
        console.log(`${CLI.color(this.getPlayerName(this.phase, true), playerColors[this.phase])} turn!`);
        this.promptAction();

    }

    /**
     * Returns the game board as a string, showing the units and bases for each player. 
     */
    toString() {

        // o o o o                   o o o
        const unitStr = `${new Array(this.units[Player.ONE]).fill('o').join(' ')}${' '.repeat(OUTPUT_WIDTH - (this.units[Player.ONE]*2 + this.units[Player.TWO]))}${new Array(this.units[Player.TWO]).fill('o').join(' ')}`
        // [] [] [] [] colored based on owner (justify across the width of the output)
        const baseSpacing = ' '.repeat(OUTPUT_WIDTH / (this.bases.length - 1));
        const baseStr = this.bases.map(base => {
            if (base === Player.ONE) {
                return CLI.color('■', playerColors[Player.ONE]);
            } else if (base === Player.TWO) {
                return CLI.color('■', playerColors[Player.TWO]);
            } else {
                return '◻';
            }
        }).join(baseSpacing);
        return '' +
        `${this.getPlayerName(Player.ONE)}${' '.repeat(OUTPUT_WIDTH - (this.getPlayerName(Player.ONE).length + this.getPlayerName(Player.TWO).length)/2)} ${this.getPlayerName(Player.TWO)}` + '\n' +
        `${unitStr}` + '\n' +
        `${baseStr}` + '\n' +
        `${this.round + 1}/${this.totalRounds} rounds\n`;

    }


}