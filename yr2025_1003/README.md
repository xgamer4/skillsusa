# UnderAssault Simulator
Simulates the outcomes of different matches in UnderAssault, allowing the user to predict the outcome of each match

## Installation

You will need to first start by making sure that you have installed Bun on your system by following the guide at [bun.sh](https://bun.sh/docs/installation). In order to ensure that you have correctly install all packages after you have installed Bun, use the command `bun install` in the root directory of this project.

## Usage

Simply run the file, and the bracket will start. Throughout the usage of this program, you will need to predict the outcome of the various sub-battles between members of teams. You can do this by typing the corresponding number at the beginning of the line for the option you would like to choose.

To run the file, use the following command in the correct directory:
```bash
bun index.ts
```

## Subcontests

The winner of each battle is decided by how the value of that individual's role proficiency. The member depicted by the value (1) on the select screen will win if the random value is lower than that member's win rate, and the member depicted by the choice (2) will win otherwise. This means that for member (1), lower is better, and for member (2), higher is better.