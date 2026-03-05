import random

from setuptools.monkey import patch_all


# 4 bases; 1 self, 1 opponent, 2 unclaimed
# Train: +1 unit/owned base
# Attack:
#   Combat resolution occurs in simulated rounds as follows:
#       1) Dice Rolls
#           a) Attacker rolls min(3, # of units) dice
#           b) Defender rolls min(2, # of units) dice
#       2) Sort die values from highest to lowest
#       3) Compare highest-value dice on attacker's side to highest-value on defenders side
#           a) If attacker value > defender value, defender loses 1 unit
#           b) if defender value >= attacker value, attacker loses 1 unit
#           c) Repeat for second-highest dice (if applicable)
#       4) Repeat steps 1-3 with update unit values until one side has 0 units left
#       5) If attacker won, opponent's base is lost and given to attacker
#       6) If defender won, nothing happens (attacker is left with 0 units)
# Expand:
#   Expanding player claims an additional unclaimed base
# (Judge Note: Expanding when no unclaimed base can either be stopped and marked invalid; do nothing; treated as attack)

# Game Start:
#   4 bases; 1 self, 1 opponent, 2 unclaimed
#   Each player starts with 1 unit

# Play occurs over 5 rounds
#   Each round is turn-based:
#   1) Player chooses an action, resolves
#   2) Opponent chooses an action, resolves
# Game ends when either:
#   1) 5 rounds have ended
#       a) Winner is player with most units
#   2) A player has 0 bases and 0 units left (in which case they lose)

class Board:
    # -1 = opponent-owned, 0 = unowned, 1 = player-owned
    base1: int
    base2: int
    base3: int
    base4: int

    player_units: int
    opponent_units: int

    round: int

    last_action_msg: str
    def __init__(self):
        self._reset_board()

    def _reset_board(self):
        self.base1 = 1
        self.base2 = -1
        self.base3 = 0
        self.base4 = 0
        self.player_units = 1
        self.opponent_units = 1
        self.round = 1
        self.last_action_msg = "Game Begins"

    def player_take(self):
        action = ""
        while action not in ["t", "e", "a", "q"]:
            action = input("Please take an action with 't', 'e', 'a', or 'q' to quit: ").lower()
        if action == "q":
            exit()
        if action == 't':
            self.last_action_msg = "Player Trains"
            if self.base1 == 1:
                self.player_units += 1
            if self.base2 == 1:
                self.player_units += 1
            if self.base3 == 1:
                self.player_units += 1
            if self.base4 == 1:
                self.player_units += 1
        elif action == 'e':
            self.last_action_msg = "Player Expands"
            if self.base1 == 0:
                self.base1 = 1
            elif self.base2 == 0:
                self.base2 = 1
            elif self.base3 == 0:
                self.base3 = 1
            elif self.base4 == 0:
                self.base4 = 1
        elif action == 'a':
            new_player, new_opponent = resolve_attack(self.player_units - 1, self.opponent_units)
            if new_player > 0:
                self.last_action_msg = "Player Attacks; Wins!"
                if self.base1 == -1:
                    self.base1 = 1
                elif self.base2 == -1:
                    self.base2 = 1
                elif self.base3 == -1:
                    self.base3 = 1
                elif self.base4 == -1:
                    self.base4 = 1
            else:
                self.last_action_msg = "Player Attacks; Loses..."
            self.player_units = new_player + 1
            self.opponent_units = new_opponent

    def opponent_take(self):
        action = random.choice(["t", "e", "a"])
        if action == 't':
            self.last_action_msg = "Opponent Trains"

            if self.base1 == -1:
                self.opponent_units += 1
            if self.base2 == -1:
                self.opponent_units += 1
            if self.base3 == -1:
                self.opponent_units += 1
            if self.base4 == -1:
                self.opponent_units += 1
        elif action == 'e':
            self.last_action_msg = "Opponent Expands"

            if self.base1 == 0:
                self.base1 = -1
            elif self.base2 == 0:
                self.base2 = -1
            elif self.base3 == 0:
                self.base3 = -1
            elif self.base4 == 0:
                self.base4 = -1
        elif action == 'a':
            new_opponent, new_player = resolve_attack(self.opponent_units-1, self.player_units)
            if new_opponent > 0:
                self.last_action_msg = "Opponent Attacks; Wins..."
                if self.base1 == 1:
                    self.base1 = -1
                elif self.base2 == 1:
                    self.base2 = -1
                elif self.base3 == 1:
                    self.base3 = -1
                elif self.base4 == 1:
                    self.base4 = -1
            else:
                self.last_action_msg = "Opponent Attacks; Loses!"
            self.player_units = new_player
            self.opponent_units = new_opponent + 1

    def has_game_ended(self) -> bool:
        if self.round >= 5:
            return True
        player_bases = 0
        opponent_bases = 0
        if self.base1 == 1:
            player_bases += 1
        elif self.base1 == -1:
            opponent_bases += 1

        if self.base2 == 1:
            player_bases += 1
        elif self.base2 == -1:
            opponent_bases += 1

        if self.base3 == 1:
            player_bases += 1
        elif self.base3 == -1:
            opponent_bases += 1

        if self.base4 == 1:
            player_bases += 1
        elif self.base4 == -1:
            opponent_bases += 1

        if self.player_units == 0 and player_bases == 0:
            return True

        if self.opponent_units == 0 and opponent_bases == 0:
            return True

        return False

    def __str__(self):
        def ownership(base_value):
            if base_value == 0:
                return "Unowned"
            elif base_value == 1:
                return "Player"
            elif base_value == -1:
                return "Opponent"
            return "Unknown"

        return f"""
            ROUND: {self.round} / 5
            
            {self.last_action_msg}
            
            Base 1: {ownership(self.base1)}
            Base 2: {ownership(self.base2)}
            Base 3: {ownership(self.base3)}
            Base 4: {ownership(self.base4)}
            
            Player Units: {self.player_units}
            Opponent Units: {self.opponent_units}
            """


def resolve_attack(attacker: int, defender: int) -> tuple[int, int]:
    """

    :param attacker: # of attacking units
    :param defender: # of defending units
    :return: tuple of remaining attacker units; defender units
    """
    attacker = max(attacker, 0)
    defender = max(defender, 0)
    if attacker == 0 or defender == 0:
        return attacker, defender

    attack_count = min(3, attacker)
    defender_count = min(2, defender)

    attack_dice = sorted([random.randint(1,6) for _ in range(attack_count)])
    defender_dice = sorted([random.randint(1,6) for _ in range(defender_count)])

    num_resolutions = min(len(attack_dice), len(defender_dice))
    for i in range(num_resolutions):
        if attack_dice[i] > defender_dice[i]:
            defender += -1
        else:
            attacker += -1

    return resolve_attack(attacker, defender)

if __name__ == '__main__':
    board = Board()
    print(board)
    for _ in range(5):
        board.player_take()
        print(board)
        board.opponent_take()
        print(board)
        has_ended = board.has_game_ended()
        board.round += 1
        if has_ended:
            board.last_action_msg = "Game ends"
            print(board)
            break