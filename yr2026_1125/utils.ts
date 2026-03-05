import * as globals from "./globals";

/**
 * Displays a selection screen, allowing for the player to choose one of the given options.
 * @param userPrompt The prompt shown to the user before they choose an option
 * @param options List of options displayed to the user
 * @returns The index of the chosen option
 */
export function playerSelect(userPrompt: string, options: { text: string; available: boolean }[]): number {
    let optionsString = userPrompt;
    for (let i = 0; i < options.length; i++) {
        const option = options[i] as { text: string; available: boolean };

        // display as "x) option"
        // grayed out if not available
        optionsString += `\n${option.available ? "" : Bun.color("#3f3f3f", "ansi")}${i + 1}) ${option.text}${globals.DEFAULT_COLOR}`;
    }

    console.log(optionsString);

    // Loop until the user enters a value that we accept
    let userNumber = null;
    while (userNumber === null) {
        try {
            let userInput = parseFloat(prompt(">") as string);

            // Checking for non integer numbers
            if (userInput % 1 !== 0) {
                console.log("Please enter an integer!");
                continue;
            }

            // Bounds check
            if (userInput > options.length || userInput <= 0) {
                console.log("Your choice is out of bounds! Please try again.");
                continue;
            }

            if (!options[userInput - 1]?.available) {
                console.log("Please choose an available option!");
                continue;
            }

            userNumber = userInput;
        } catch (error) {
            console.log("Invalid input! Please try again.");
        }
    }

    // -1 to make it an index in the list rather than what the user sees
    return userNumber - 1;
}

// Source - https://stackoverflow.com/a/36481059
// Standard Normal variate using Box-Muller transform.
export function gaussianRandom(mean = 0, stdev = 1) {
    const u = 1 - Math.random(); // Converting [0,1) to (0,1]
    const v = Math.random();
    const z = Math.sqrt(-2.0 * Math.log(u)) * Math.cos(2.0 * Math.PI * v);
    // Transform to the desired mean and standard deviation:
    return z * stdev + mean;
}
