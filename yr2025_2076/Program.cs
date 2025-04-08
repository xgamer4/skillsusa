using System;
using System.Collections.Generic;
using static System.Console;

class FireStormUnderAssult
{
    // The random object for the whole program
    static Random randomNumber = new Random();

    static void Main(string[] args)
    {
        // Sets up teams
        Team[] teams = {
            new Team("Fuel"),
            new Team("Outlaws"),
            new Team("Shock"),
            new Team("Dynasty")
        };


        SimulateTournament(teams);
    }

    static void SimulateTournament(Team[] teams)
    {
        Team[][] remainingMatches = FormBrackets(teams);

        int roundsCompleted = 0;
        while (remainingMatches.Length > 0)
        {
            // Displays the matches in the brackets
            WriteLine($"Round: {roundsCompleted + 1}");
            foreach (Team[] match in remainingMatches)
            {
                Write($"{match[0].TeamName} vs {match[1].TeamName}  ");
            }
            WriteLine("\n");

            List<Team> winningTeams = new List<Team>();
            // Runs the matches 
            foreach (Team[] bracketTeams in remainingMatches)
            {
                Team teamOne = bracketTeams[0];
                Team teamTwo = bracketTeams[1];


                // User guesses who will win
                Write($"Who will Win? {teamOne.TeamName} or {teamTwo.TeamName}: ");
                string userGuess;
                do
                {
                    userGuess = ReadLine().ToLower();
                    if (userGuess != teamOne.TeamName.ToLower() && userGuess != teamTwo.TeamName.ToLower())
                    {
                        Write("Enter the team you guess will win: ");
                    }
                } while (userGuess != teamOne.TeamName.ToLower() && userGuess != teamTwo.TeamName.ToLower());

                // Match is simulated
                Team matchWinner = SimulateMatch(teamOne, teamTwo);

                // Declares winner
                WriteLine($"\n{matchWinner.TeamName} Wins\n");
                winningTeams.Add(matchWinner);


                if (userGuess == matchWinner.TeamName.ToLower())
                {
                    WriteLine("YOU GUESSED RIGHT!!!");
                }
                else
                {
                    WriteLine("your guess was wrong...");
                }
                WriteLine("\n");
            }

            // The winners of each match form a new bracket
            remainingMatches = FormBrackets(winningTeams.ToArray());
            roundsCompleted++;
        }
    }

    // Pairs off two teams into an array
    static Team[][] FormBrackets(Team[] teams)
    {
        Team[][] matches = new Team[teams.Length / 2][];
        for (int i = 0; i < matches.Length; i++)
        {

            matches[i] = new Team[] { teams[i * 2], teams[i * 2 + 1] };
        }

        return matches;
    }


    static Team SimulateMatch(Team teamOne, Team teamTwo)
    {
        int maxGameRounds = 3;
        double gamesNeededToWin = Math.Round((double)maxGameRounds / 2);

        // How many times team one has won
        int gamesWonByOne = 0;

        // Each iteration is a game played and will end if either player wins twice
        for (int i = 0; i < 3 && gamesWonByOne < gamesNeededToWin && gamesWonByOne - i > -gamesNeededToWin; i++)
        {
            int team1Wins = 0;

            // DPS vs Tank
            team1Wins += EnemyInteraction(teamOne.GetPlayerWithRole(Player.Role.DPS).PrimaryStat, teamTwo.GetPlayerWithRole(Player.Role.Tank).PrimaryStat);
            // Tank vs DPS
            team1Wins += EnemyInteraction(teamOne.GetPlayerWithRole(Player.Role.Tank).PrimaryStat, teamTwo.GetPlayerWithRole(Player.Role.DPS).PrimaryStat);
            // Support Vs Support
            team1Wins += EnemyInteraction(teamOne.GetPlayerWithRole(Player.Role.Support).PrimaryStat, teamTwo.GetPlayerWithRole(Player.Role.Support).PrimaryStat);

            Team gameWinner;
            if (team1Wins >= 2)
            {
                // Team one won game
                gameWinner = teamOne;
                gamesWonByOne++;
            }
            else
            {
                // Team two won game
                gameWinner = teamTwo;
            }

            WriteLine($"Team {gameWinner.TeamName} Won game {i + 1}");
        }

        // Returns winner of match
        if (gamesWonByOne >= gamesNeededToWin)
        {
            return teamOne;
        }
        else
        {
            return teamTwo;
        }

    }


    // Outputs the winner of an encounter
    static int EnemyInteraction(int teamOneStat, int teamTwoStat)
    {
        // Team one's chance at winning
        double winChance = (double)teamOneStat / (teamOneStat + teamTwoStat);

        if (randomNumber.NextDouble() <= winChance)
        {
            // Team one Wins
            return 1;
        }
        else
        {
            // Team one Doesn't win
            return 0;
        }
    }

    class Team
    {
        Player[] teamPlayers = new Player[3];

        public string TeamName { get; private set; }

        public void PrintTeamStats()
        {
            WriteLine(TeamName);
            foreach (Player player in teamPlayers)
            {
                player.PrintStats();
            }
        }

        void SetPlayersToBestRole()
        {
            // Best player for Tank, Support, and DPS
            List<Player>[] bestForRole = new List<Player>[3];

            for (int i = 0; i < bestForRole.Length; i++)
            {
                bestForRole[i] = new List<Player>();
            }

            // Iterates roles
            for (int i = 0; i < 3; i++)
            {
                // Iterates players
                for (int x = 0; x < teamPlayers.Length; x++)
                {
                    bestForRole[i].Add(teamPlayers[x]);
                }

                // Sorts the best player for each role to the end of each list
                bestForRole[i].Sort((a, b) => a.Proficiencies[i].CompareTo(b.Proficiencies[i]));

            }

            // Assigns team roles favoring first in line i.e. tank
            List<Player> playersWithRole = new List<Player>();
            for (int i = 0; i < bestForRole.Length; i++)
            {
                bool playerAdded = false;
                // As the list is sorted from most proficient last we start at the end
                for (int x = bestForRole.Length - 1; !playerAdded; x--)
                {
                    // If the best player for a role doesn't already have a role give them one
                    if (!playersWithRole.Contains(bestForRole[i][x]))
                    {
                        bestForRole[i][x].TeamRole = (Player.Role)i;

                        // remembers the player to avoid giving him another role
                        playersWithRole.Add(bestForRole[i][x]);
                        playerAdded = true;
                    }
                }
            }
        }

        // Returns team member with specified role
        public Player GetPlayerWithRole(Player.Role teamRole)
        {
            foreach (Player player in teamPlayers)
            {
                if (player.TeamRole == teamRole)
                {
                    return player;
                }
            }
            return null;

        }

        // Team constructor
        public Team(string teamName)
        {
            TeamName = teamName;

            // Initializes Team players
            for (int i = 0; i < teamPlayers.Length; i++)
            {
                teamPlayers[i] = new Player();
            }

            SetPlayersToBestRole();
        }
    }

    class Player
    {
        public enum Role
        {
            Tank,
            Support,
            DPS
        }

        public Role TeamRole { get; set; }

        // Stores Tank, Support, DPS proficiencies in that order, matching Role enum order
        public int[] Proficiencies { get; set; } = new int[3];

        // If the player is a DPS it will return the DPS stat
        public int PrimaryStat { get { return Proficiencies[(int)TeamRole]; } }

        // Displays stats to command line
        public void PrintStats()
        {
            WriteLine("Role: " + TeamRole);

            for (int i = 0; i < Proficiencies.Length; i++)
            {
                WriteLine($"{(Role)i}: {Proficiencies[i]}");
            }
            WriteLine();
        }

        // Player constructor
        public Player()
        {
            // Give player random stats
            for (int i = 0; i < Proficiencies.Length; i++)
            {
                Proficiencies[i] = randomNumber.Next(0, 101);
            }
        }

    }

}