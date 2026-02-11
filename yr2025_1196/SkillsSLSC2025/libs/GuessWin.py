class GuessWin:
    """
    ***************************************************
    Takes the four teams, and allows the user to
    make their guesses on the winner then it will store
    the users guesses in a tuple

    Parameters: 4 Teams objs
    Return: None
    ***************************************************
    """
    def __init__(self,team1,team2,team3,team4):
        while True:

            match1 = (team1,team2)
            try:
                guess_one = int(input("Based on the stats listed above who do you think will will win round one \n" 
                          f"1) {team1.name}\n"
                          f"2)  {team2.name}\n")) -1
                guess_one = match1[guess_one]
                break

            except ValueError:
                print("Please enter a number.")
            except IndexError:
                print("Please enter either a 1 or 2 ")

        while True:
            match2 = (team3,team4)
            try:
                guess_two = int(input("Based on the stats listed above who do you think will will win round two \n"
                                      f"1) {team3.name}\n"
                                      f"2)  {team4.name}\n")) - 1
                guess_two = match2[guess_two]
                break

            except ValueError:
                print("Please enter a number.")
            except IndexError:
                print("Please enter either a 1 or 2 ")

        while True:
            final_match = (guess_one,guess_two)

            try:
                guess_final = int(input("Based on the stats listed above who do you think will win the finals \n"
                                      f"1) {guess_one.name}\n"
                                      f"2)  {guess_two.name}\n")) - 1
                guess_final = final_match[guess_final]
                break

            except ValueError:
                print("Please enter a number.")
            except IndexError:
                print("Please enter either a 1 or 2 ")


        self.stats = (guess_one,guess_two,guess_final)

    """
    ***************************************************
    Takes the three rounds, and compares them to the
    user's guesses for how the bracket matches will go
    once comparisons are displayed prints out a accuracy
    score as a percentage
    
    Parameters: The results of the three rounds
    Return: None
    ***************************************************

    """
    def check_guess_accuracy(self,round1,round2,round3):
        correct_guess = 0
        rounds = (round1,round2,round3)
        if self.stats[0] == rounds[0]:
            print("You guessed correctly for round one!\n")
            correct_guess += 1
        else:
            print("You guessed incorrectly for round one!\n")
        if self.stats[1] == rounds[1]:
            print("You guessed correctly for round two!\n")
            correct_guess += 1
        else:
            print("You guessed incorrectly for round two!\n")
        if self.stats[2] == rounds[2]:
            print("You guessed correctly for round three!\n")
            if self.stats[0] != rounds[0] or rounds[1] != self.stats[1]:
                print("However one of your prior guesses was incorrect so only 1/2 point")
                correct_guess += 0.5
            else:
                correct_guess += 1
        else:
            print("You guessed incorrectly for round three!\n")
        print(f"You had a accuracy rating of {correct_guess/3 * 100}%")


