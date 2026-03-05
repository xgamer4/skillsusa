import { ARG_NO_WAIT } from "./arguments";
import { CLI } from "./CLI";
import { Action, Game } from "./Game";
import { PlayerInterface } from "./PlayerInterface";

export class AI implements PlayerInterface {
    name = 'Computer';
    constructor(name?: string) {
        // provide a custom name if desirede
        if (name) {
            this.name = name;
        }
    }
    async promptAction(game: Game): Promise<Action> {
        // simulate thinking time
        await this.think();
        // if has unclaimed based, 85% chance to expand
        const hasUnclaimedBases = game.bases.some(base => base === null);
        if (hasUnclaimedBases && Math.random() < 0.85) {
            return Action.EXPAND;
        }
        // if has enough units to attack, 50% chance to attack increased by 25% for each additional unit over the opponent and increased by 25% for the current round number
        const canAttack = game.units[game.phase] >= 2;
        if (canAttack && Math.random() < 0.5 + ((game.units[game.phase] - game.units[game.phase === 0 ? 1 : 0]) * 0.25) + (game.round * 0.25)) {
            return Action.ATTACK;
        }
        // otherwise, train
        return Action.TRAIN;
        
    }

    private async think() {
        if (ARG_NO_WAIT) return;
         await new Promise(async resolve => {
            const thinkingTotal = Math.min(2, Math.random() * 10);
            // make a loop of ... that clears once it reaches 3 for thinking total
            let thinkingStr = '';
            const interval = setInterval(() => {
                thinkingStr += '.';
                if (thinkingStr.length > 3) {
                    thinkingStr = '';
                    process.stdout.clearLine(0);
                }
                process.stdout.write(`\r${CLI.color(`${this.name} is thinking${thinkingStr}`, 'yellow')}`);
            }, 250);
            setTimeout(() => {
                clearInterval(interval);
                process.stdout.clearLine(0);
                process.stdout.cursorTo(0);
                resolve(null);
            }, thinkingTotal * 1000);
        });
    }
}