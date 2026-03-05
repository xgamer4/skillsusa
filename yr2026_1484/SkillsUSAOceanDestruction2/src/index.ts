import { Game } from "./Game";
import { AI } from "./AI";
import { User } from "./User";
import { CLI } from "./CLI";

async function main() {
    const numOfPlayers = await CLI.ask('How many players? (0, 1 or 2): ', CLI.validators.enum(['0', '1', '2']));
    if (numOfPlayers === '0') {
        new Game(new AI('Computer 1'), new AI('Computer 2'));
    } else if (numOfPlayers === '1') {
        new Game(new User(), new AI('Computer'));
    } else {
        const player1Name = await CLI.ask('Enter name for Player 1: ', CLI.validators.text(12, 1));
        const player2Name = await CLI.ask('Enter name for Player 2: ', CLI.validators.text(12, 1));
        new Game(new User(player1Name), new User(player2Name));
    }
}
main();

