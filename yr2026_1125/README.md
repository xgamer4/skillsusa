# OceanDestruction 2 Simulator

This project aims to be a OceanDestruction 2 simulator in hopes of providing an accurate enough simulation to be used to plan strategies

## Installation

This project uses `bun.sh`, which is similar to the node runtime. You can install Bun at [https://bun.com](https://bun.com).

After you have installed bun, you can run the project by running the following commands:

```bash
bun install
bun run index.ts
```

## Usage

After you have launched the program, you are able to interact with it by typing in numbers to select your course of action on your current turn. If an option is grayed out, you will be unable to select it.

A player wins immediately if the opponent has 0 units and 0 bases.

After 5 turns from both players, the game will also end. Winning is then determined by who has more units, and then more bases. If both are equal, the result will be a tie.

## Sources

This project uses some code snippets from StackOverflow, noted in the inline comments and below:

* [Carriage return](https://stackoverflow.com/a/32938658)
* [Standard deviation](https://stackoverflow.com/a/36481059)
