import { Team } from "./team.ts";
import { Bracket } from "./bracket.ts";

// these are the teams that will be in the bracket
const teams = [
    new Team("the Fuel", Bun.color("#2a5eed", "ansi") as string),
    new Team("the Outlaws", Bun.color("#ed6b2a", "ansi") as string),
    new Team("the Shock", Bun.color("#e5d62b", "ansi") as string),
    new Team("the Dynasty", Bun.color("#ed2a2a", "ansi") as string)
];

// start the bracket with the given teams
const bracket = new Bracket(teams);
await bracket.start();