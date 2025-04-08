# Firestorm UnderAssault Bracket Simulator
- Created by contestant #1013
- SkillsUSA Idaho State Competition
- 8 April 2025

## About
This is a tool that can be used to better predict which teams will win which brackets in the fictional game *Firestorm 
UnderAssault* for the 2025 Idaho State SkillsUSA Computer Programming competition. There is a bracket between four 
teams: Fuel, Outlaws, Shock, and Dynasty with three members on each team: the DPS, Tank, and Support. Each match is a
best of 3 competition between Team 1's DPS vs Team 2's Tank, Team 1's Tank vs Team 2's DPS, and Team 1's Support vs Team
2's Support. There are three brackets: Fuel vs Outlaws and Shock vs Dynasty for the start, and the final bracket is 
comprised of whoever won from the previous two brackets versus each other.

## Compilation and Running
In order to compile the program for use you must run navigate to the project directory in the terminal of your choice
and enter the following command:
```shell
# CLI Version
javac BracketSimulator.java
```
In order to run the compiled program you must enter the following command:
```shell
# CLI Version
java BracketSimulator
```

## External Sources Used
- [StackOverflow - How do I make a delay in Java?](https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java)
- [How to Make Dialogs](https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html)
- [How to Use BoxLayout](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)