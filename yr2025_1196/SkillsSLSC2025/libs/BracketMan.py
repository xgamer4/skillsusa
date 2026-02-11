from random import randint as ran

class BracketComp:

    """
    ***************************************************
    Simulates each round of bracket compensation.

    Takes a tuple/ list containing the two competing
    team obj as input.

    It will then use the stats of each item to calculate
    the score and  add a point to the winning team

    When the round is over it will return the team obj
    that wins

    Parameters: Tuple/ List containing team objs
    Returns: The winning team obj
    ***************************************************

    """
    def round_simulator(self, lineup):
        #Fetches the two competing teams
        team_one = lineup[0]
        team_two = lineup[1]

        # Start the tally at 0 and add per win used at the end
        team_one_wins = 0
        team_two_wins = 0

        # Team 1's DPS vs Team 2's Tank

        # Pulls the stats required from the team obj generated earlier
        dps_prof = team_one.dps['dps_prof']
        tank_prof = team_two.tank['tank_prof']

        #I set the values in separate variables to keep code a little cleaner

        #Score Calculation
        score = dps_prof / (dps_prof + tank_prof)

        #Min score required for team 1 to win
        score_floor = ran(0,1)

        # If team two scores higher than score floor they get a point
        if  score >= score_floor:
            team_two_wins += 1
        else:
            # If not team two gets the point
            team_one_wins += 1

        # Team 1's Tank vs Team 2's DPS

        tank_prof = team_two.dps['dps_prof']
        dps_prof = team_one.tank['tank_prof']

        # Score calculation
        score = tank_prof / (dps_prof + tank_prof)

        # Min score required for team to win
        score_floor = ran(0,1)

        # If team one scores higher than the floor they get a point
        if score >= score_floor:
            team_one_wins += 1
        # If not team two gets the point
        else:
            team_two_wins += 1

        # Team 1's Support vs Team 2's Support
        support_1 = team_one.support["support_prof"]
        support_2 = team_two.support["support_prof"]

        #Score calculation
        score = support_1 / (support_1 + support_2)

        #Min score required for team to win
        score_floor = ran(0,1)

        # If team one scores higher than the floor they get a point
        if score >= score_floor:
            team_one_wins += 1
        # If not team two gets the point
        else:
            team_two_wins += 1

        #Checks the final score to see who wins
        if team_one_wins > team_two_wins:
            return team_one
        elif team_two_wins > team_one_wins:
            return team_two


