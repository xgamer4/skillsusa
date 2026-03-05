
export function justifyBetween(words: string[], maxWidth: number) {
    const numWords = words.length;

    // Handle single word or empty array case
    if (numWords <= 1) {
        return words.length === 0 ? "" : words[0]!.padEnd(maxWidth, ' ');
    }

    // Calculate total length of words
    const totalWordsLength = words.reduce((sum, word) => sum + word.length, 0);

    // Calculate total available space for padding
    const totalSpaces = maxWidth - totalWordsLength;

    // Calculate minimum spaces required between each word
    const minSpacesPerInterval = Math.floor(totalSpaces / (numWords - 1));

    // Calculate extra spaces to be distributed (left to right)
    let extraSpaces = totalSpaces % (numWords - 1);

    let result = "";

    for (let i = 0; i < numWords; i++) {
        result += words[i];

        // Add padding to all words except the last one
        if (i < numWords - 1) {
            let padding = minSpacesPerInterval;
            // Distribute extra spaces to intervals on the left
            if (extraSpaces > 0) {
                padding++;
                extraSpaces--;
            }
            // Use padEnd to add the calculated spaces
            result += "".padEnd(padding, ' ');
        }
    }

    return result;
}


// print each number in a box horizontally aligned
export function printDice(rolls: number[]) {
    const top = rolls.map(() => '┌───┐').join(' ');
    const middle = rolls.map(roll => `│ ${roll} │`).join(' ');
    const bottom = rolls.map(() => '└───┘').join(' ');

    console.log(top);
    console.log(middle);
    console.log(bottom);
}

export function printBattle(attackerRoll: number, defenderRoll: number) {
    let top = '';
    let middle = '';
    let bottom = '';

   // show die side by side with VS in the middle
    top +=    '┌───┐    ┌───┐';
    middle += `│ ${attackerRoll} │ VS │ ${defenderRoll} │`;
    bottom += '└───┘    └───┘';

    console.log(top);
    console.log(middle);
    console.log(bottom);
}

export function capitalize(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

export function boxed(text: string, padding = 1) {
    const lines = text.split('\n');
    const maxLength = Math.max(...lines.map(line => line.length));
    const horizontalBorder = '─'.repeat(maxLength + padding * 2);
    const emptyLine = '│' + ' '.repeat(maxLength + padding * 2) + '│';
    let string = '';
    string += '┌' + horizontalBorder + '┐\n';
    for (let i = 0; i < padding; i++) {
        string += emptyLine + '\n';
    }
    lines.forEach(line => {
        const paddedLine = line + ' '.repeat(maxLength - line.length);
        string += '│' + ' '.repeat(padding) + paddedLine + ' '.repeat(padding) + '│\n';
    });
    for (let i = 0; i < padding; i++) {
        string += emptyLine + '\n';
    }
    string += '└' + horizontalBorder + '┘';
    return string;
}