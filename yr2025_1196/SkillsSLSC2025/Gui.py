import streamlit as st
from libs.TeamGen import TeamGeneration
from libs.BracketMan import BracketComp

st.header("Gui")

bracketeer = BracketComp()


def check_guess_accuracy(g1,g2,g3,round1, round2, round3):
    correct_guess = 0
    rounds = (round1, round2, round3)
    if g1 == rounds[0]:
        correct_guess += 1
    if g2 == rounds[1]:
        correct_guess += 1
    if g3 == rounds[2]:
        if g1 != rounds[0] or g2 != rounds[1]:
            correct_guess += 0.5
        else:
            correct_guess += 1
    else:
        return correct_guess

@st.cache_data
def store_objs(_team1, _team2, _team3, _team4):
    return _team1, _team2, _team3, _team4

@st.cache_data
def store_guesses(_guess1, _guess2, _guess3):
    return _guess1, _guess2, _guess3

with st.form("Guess Form") as form:
    team_fuel = TeamGeneration("Fuel")
    team_outlaws = TeamGeneration("Outlaws")
    team_shock = TeamGeneration("Shock")
    team_dynasty = TeamGeneration("Dynasty")

    store_objs(team_fuel, team_outlaws, team_shock, team_dynasty)


    match1 = (team_fuel,team_outlaws)
    guess1 = st.radio(label="Who do you think will will round 1?", options=match1)

    match2 = (team_shock,team_dynasty)
    guess2= st.radio(label="Who do you think will will round 2?", options=match2)

    match3 = (guess1,guess2)
    final_guess = st.radio(label="Who do you think will round 3?", options=match3)

    submitted = st.form_submit_button(label="Submit guess")

    if submitted:
        store_guesses(guess1,guess2,final_guess)
        st.write("Guesses recorded ")

        winner_one = bracketeer.round_simulator(match1)
        st.write(f"Round One Winner {winner_one.name}")

        winner_two = bracketeer.round_simulator(match2)
        st.write(f"Round Two Winner {winner_two.name}")

        winner_three = bracketeer.round_simulator(match3)
        st.write(f"Round Three Winner {winner_three.name}")

        accuracy = check_guess_accuracy(guess1,guess2,final_guess,winner_one, winner_two, winner_three)
        st.write("You have a accuracy of " + str(accuracy) + "%")

