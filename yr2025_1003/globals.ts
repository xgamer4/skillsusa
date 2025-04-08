import { Menu } from "./menu.ts";

export const MENU = new Menu();

// source: https://github.com/janelleshane/DnD-characters/
export const NAMES_CSV = (await Bun.file("names/dndchars.csv").text()).replaceAll("\r", "").split("\n");
export const NAMES: Array<string> = [];

// go through the CSV and add to the list of names
for (let i = 1; i < NAMES_CSV.length; i++) {
    const line = NAMES_CSV[i];
    if (line == undefined) continue;

    const name = line.split(",")[0]?.trim();
    if (name == undefined) continue;
    if (name.length > 32) continue;

    NAMES.push(name);
}

export const MEMBER_ROLES = {
    DPS: 0,
    TANK: 1,
    SUPPORT: 2
};

// a dict of what role should be battling what role
export const BATTLE_OPPOSING_ROLE = {
    "TANK": "DPS",
    "DPS": "TANK",
    "SUPPORT": "SUPPORT"
};

export const COLORS = {
    TEAM: Bun.color("#18a7e0", "ansi"),
    NORMAL: Bun.color("lightgray", "ansi")
};

export const wheel_spin = async () => {
    const spin_number = 24;
    let selected = -1;
    
    // go through 24 numbers and "spin" the wheel
    for (let i = 0; i < spin_number; i++) {
        selected = Math.random();
        console.write("\r" + Math.round(selected * 100) + "%  ");

        await Bun.sleep(i ** 2);
    }

    return selected;
};
// await wheel_spin(100);


export const select_from_options = (input_prompt: string, options: Array<string>): number => {
    let user_choice = "";
    const out = input_prompt + options.map((value, index) => `\n${index+1}) ${value}`).join("");

    while (true) {
        // show the prompt and collect user input
        console.log(out);
        user_choice = prompt(">") ?? "";

        try {
            const index = parseInt(user_choice) - 1;

            if (isNaN(index)) {
                console.log("Invalid input, please input a number\n");
                continue;
            }
            
            if (index > options.length - 1 || index < 0) {
                console.log("Please input a number in the given range\n");
                continue;
            }

            return index;

        } catch {
            console.log("Invalid input, please input a number\n");
            continue;
        }
    }
};