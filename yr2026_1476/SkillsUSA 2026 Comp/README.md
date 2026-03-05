# Ocean Destruction 2

Welcome to Ocean Destruction 2, a very simple text-based RTS Game.

# Description

Ocean Destruction 2 is a simple text-based RTS Video Game that is played within the terminal of VS Code (or other code executing application).
The game has two players, the User and the Opponent. The User is controlled by the person playing through in-line terminal command execution. The Opponent is controlled via a very rudimentary AI that performs actions based on a "desire value" for each action.
Each turn, you may train troops, attack the enemy, or expand and gain a new base.
At the end of the game, whoever has the most troops, followed by bases, will win. It is possible to tie, although very unlikely.

## Dependencies

The only dependency for the project should be having VS Code as well as Java 25.0.1, as that is the version it was programmed in. It is unknown whether earlier versions would work or not.

## Launching the Game

In order to launch the game, install all files into one folder and open VS Code. From there, input the following command:
`java Runner.java (your player name)`, or alternatively, `java Runner.java (your player name) (opponent name)`.
Note - If you do not include an opponent name, one will be randomly selected.

# Credits

Designed, tested, and programmed by **Gamma St. Clair**.
Prompt given by **SkilsUSA**.

- https://www.w3schools.com/java/ref_string_touppercase.asp
- https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc
