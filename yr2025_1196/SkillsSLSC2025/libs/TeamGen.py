from random import randint as ran

class TeamGeneration:

    """
    ***************************************************
    Creates an instance of the team generation class.
    Generates the team members, and their proficiency.
    scores

    Parameters: Team Name
    Returns: N/A
    ***************************************************
    """
    def __init__(self,name):
        self.name = name
        #Generates the three team members, and their respective stats (stats are stored in a dictionary)
        self.dps = {"dps_prof": ran(0,100),"tank_prof": ran(0,100), "support_prof": ran(0,100)}
        self.tank = {"dps_prof": ran(0,100),"tank_prof": ran(0,100), "support_prof": ran(0,100)}
        self.support = {"dps_prof": ran(0,100),"tank_prof": ran(0,100), "support_prof": ran(0,100)}


    """
    ***************************************************
    Formats the obj defined in __init__, and makes it 
    readable to the user. Used __str__ method so you
    can just use print(obj) rather than obj.print() 
    
    Parameters: N/A
    Returns: A formatted string
    ***************************************************
    """
    def __str__(self):
        return \
f"""
Team: {self.name}
    DPS: {self.dps}
    Tank: {self.tank}
    Support: {self.support}
"""
#I have to have the multiline string like this or python will format it with extra spacing.