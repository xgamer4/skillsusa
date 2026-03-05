import { capitalize } from "./lib";

interface Options<T> {
        [key: string]: {
            short: string;
            description: string;
            value: T
        };
    
}
export class CLI {
    static validators = {
        enum: (options: string[]) => (input: string) => options.includes(input),
        text: (max = 12, min = 0) => (input: string) => typeof input === 'string' && input.length >= min && input.length <= max,
    }
    /*
        * Prompts the user for input from stdin and returns the input as a string.
    */
    static async prompt(message: string): Promise<string> {
        return new Promise((resolve) => {
            process.stdin.resume();
            process.stdout.write(message);
            process.stdin.once('data', (data) => {
                resolve(data.toString().trim());
            });
        }); 
    }
    static async ask(message: string, validator?: (input: string) => boolean): Promise<string> {
        while (true) {
            const input = await CLI.prompt(message);
            if (!validator || validator(input)) {
                return input;
            }
            console.log('Invalid input, please try again.');
        }
    }
    static async menu<T>(options: Options<T>): Promise<T> {
        Object.entries(options).forEach(([key, option]) => {
            console.log(`${capitalize(key).padEnd(10)} (${option.short}) ${option.description}`);
        });
        while (true) {
            const input = await CLI.prompt('Choose an option: ');
            const option = Object.values(options).find(opt => opt.short === input || opt.value === input);
            if (option) {
                return option.value;
            }
            console.log('Invalid option, please try again.');
        }
    }
    static color(text: string, color: string) {
        const colors: { [key: string]: string } = {
            red: '\x1b[31m',
            blue: '\x1b[34m',
            reset: '\x1b[0m',
        };
        return `${colors[color] || colors.reset}${text}${colors.reset}`;
    }
}