import { Game, PlayerAction } from "./Game";
import { playerSelect, gaussianRandom } from "./utils";
import { Player } from "./Player";
import * as globals from "./globals";

/**
 * Represents a bot player in the game of OceanDestruction 2
 */
export class BotPlayer extends Player {
    constructor(game: Game, unitCount: number, playerName: number, color: string | null) {
        super(game, unitCount, playerName, color);
        this.isBot = true;
    }

    /**
     * Allows to bot to choose an action from the list of possible actions
     * @param actions The possible actions that should be chosen from
     * @returns The action that is being made
     */
    override async makeAction(actions: { action: PlayerAction; available: boolean }[]): Promise<PlayerAction> {
        let state = 0;

        const timerAnimationTick = () => {
            let waitingString = `${this.getLongName()} is thinking`;
            for (let i = 0; i < state; i++) waitingString += ".";

            state = (state + 1) % 4;
            // Generously provided by https://stackoverflow.com/a/32938658
            process.stdout.write("\r\x1b[K" + waitingString);
        };

        const timerAnimation = setInterval(timerAnimationTick, 400);
        console.log(); // add a blank line since waiting text will replace the current line
        timerAnimationTick();

        // Wait to simulate more intense logic happening here
        await Bun.sleep(Math.max(Math.min(gaussianRandom(1500, 1000), 2000), 400));
        clearInterval(timerAnimation);

        // Remove unavailable options
        actions = actions.filter((v) => v.available);

        // Return a random action
        return (
            actions[Math.floor(Math.random() * actions.length)] as {
                action: PlayerAction;
            }
        ).action;
    }
}
