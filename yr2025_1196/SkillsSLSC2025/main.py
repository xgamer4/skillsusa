from libs.TeamGen import TeamGeneration
from libs.BracketMan import BracketComp
from libs.GuessWin import GuessWin

while True:

    #Generates the four team objs using TeamGen.py
    team_fuel = TeamGeneration("Fuel")
    team_outlaws = TeamGeneration("Outlaws")
    team_shock = TeamGeneration("Shock")
    team_dynasty = TeamGeneration("Dynasty")

    #Prints the formated version of the objs to the screen
    print(team_fuel)
    print(team_outlaws)
    print(team_shock)
    print(team_dynasty)


    input("Press enter to continue...")

    #Runs the bracket guesser found in GuessWin.py
    guesser = GuessWin(team_fuel, team_outlaws, team_shock, team_dynasty)

    input("Press enter to continue...\nh")

    # inits the bracket competition manager found in BracketMan.py
    bracketeer = BracketComp()

    input(f"Round 1: {team_fuel.name} VS {team_outlaws.name} \n"
          "Press enter to continue...\n")

    #Runs the first bracket and returns the winning obj
    round1 = (team_fuel,team_outlaws)
    winner1 = bracketeer.round_simulator(round1)

    input(f"Round 1 Winner!: {winner1.name} \n "
          "Press enter to continue...\n")

    input(f"Round 2: {team_shock.name} VS {team_dynasty.name} \n"
          "Press enter to continue...\n")

    #Runs the second bracket and returns the winning obj
    round2 = (team_shock,team_dynasty)
    winner2 = bracketeer.round_simulator(round2)

    input(f"Round 2 Winner!: {winner2.name} \n"
          "Press enter to continue...\n")

    input(f"Final Match: {winner1.name} VS {winner2.name} \n"
          "Press enter to continue...\n")

    #Runs the final bracket and returns the winning obj
    final_round = (winner1,winner2)
    winner = bracketeer.round_simulator(final_round)

    input(f"Final Match Winner!:  {winner.name}\n"
          "Press enter to continue...\n")


    #Calls the guess checker found in GuessWin.py, and displays accuracy report
    guesser.check_guess_accuracy(winner1,winner2,winner)

    #Checks if the user would like to play again.
    again = input("\n Would you like to play again? ")
    try:
        if again[0].upper() == "N":
            exit("\n Thank you for playing! Have a nice day!")
    except IndexError:
        continue
    except:
        exit("ERROR UNKNOWN EXITING")