# FireStorm UnderAssault
This project simulates a tournament bracket for an fps game called UnderAssault, where a team fights 
another team both teams have 1 tank, 1 dps, and 1 support and they compete to win a best of 3 matches
in any match the way to calculate the winner you must compare the tank of team1 to the dps of team2
then the dps of team1 to the tank of team2 and finally the supports from both teams whichever 
team wins 2 of these 'sub contests' wins the match.

When a team is created, it also creates three players, the players are assigned rolesin the order they are 
made, so player1 will be tank player2 will be dps and player3 will be support

I tried to optimize these teams using a Hungarian algorithm, but I didn't have enough time to work one out
I also am unfamiliar with the algorithm, and would rather have a finished not super optimized project, than
an unfinished project that was going to be optimized.
If you'd like to see my attempt at the algorithm it is in the 'Unused Hungarian Algorithm.txt' file.
The algorithm is originally meant to minimize the values in a matrix, so I tried to repurpose it for maximal
values, but I couldn't finish it without some poorly crafted solutions.

# compilation
This program is run and compiled through the terminal, the commands are all clearly defined type 'h' for help

# features
allows a user to simulate a tournament bracket in UnderAssault with 4 teams (Dallas Fuel, Houston Outlaws, 
San Francisco Shock, and Seoul Dynasty) you can predict the winner of any given best of 3 match
and the final winner of the tournament. You can also easily reset by typing 's' in the command line
to start a new bracket.