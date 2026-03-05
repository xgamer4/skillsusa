import { CLI } from "./CLI";
import { Action, Game } from "./Game";
import { PlayerInterface } from "./PlayerInterface";

export class User implements PlayerInterface {
    name = 'You';
    possesive = 'Your';
    constructor(name?: string) {
        // provide a custom name if desired, or when playing in versus mode
        if (name) {
            this.name = name;
        }
    }
    async promptAction(game: Game): Promise<Action> {
        console.log(game.toString());
        return await CLI.menu({
           
                'train': {
                    short: 't',
                    description: 'Train units',
                    value: Action.TRAIN,
                },
                'attack': {
                    short: 'a',
                    description: 'Attack opponent',
                    value: Action.ATTACK,
                },
                'expand': {
                    short: 'e',
                    description: 'Expand to new base',
                    value: Action.EXPAND,
                },
                'quit': {
                    short: 'q',
                    description: 'Quit game',
                    value: Action.QUIT,
                
            }    
        })
    }   
}