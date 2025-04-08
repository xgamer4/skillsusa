import { Team } from "./team.ts";
import { Member } from "./member.ts";
import { BATTLE_OPPOSING_ROLE, MEMBER_ROLES, select_from_options, MENU, COLORS, wheel_spin } from "./globals.ts";

export class Match {
    teams: [Team, Team];
    scores: [number, number];

    constructor(teams: [Team, Team]) {
        this.teams = teams;
        this.scores = [0, 0];
    }

    /**
     * Lets the user input their prediction for who will win each contest
     * @returns 
     */
    private predict_match_winner(): 0|1 {
        const team_strings = [];
        for (let team of this.teams) {
            const members_string = team.members.values().toArray().map((m) => m.name).join(", ");
            team_strings.push(`${team.print_name()} (${members_string})`);
        }

        const user_choice = select_from_options("Predict a winner from the below teams: ", team_strings) as 0|1;
        return user_choice;
    }

    /**
     * Allows the user to input their prediction for who will win the given subcontest
     * @param members 
     * @returns 
     */
    private predict_subcontest_winner(members: [Member, Member]): 0|1 {
        const user_choice = select_from_options(
            "Predict a winner from the below contestants",
            members.map((m, i) => `${m.name} as ${Bun.color("white", "ansi")}${m.role.toLowerCase()}${Bun.color("lightgray", "ansi")} (${this.teams[i]?.print_name()})`)
        ) as 0|1;
        return user_choice;
    }

    /**
     * Determine the winner of a given subcontest, along with getting the player's prediction for it
     * @param member 
     * @param opposingMember 
     * @returns 
     */
    private async determine_subcontest(member: Member, opposingMember: Member) {
        const subcontest_prediction = this.predict_subcontest_winner([member, opposingMember]);

        const win_chance = member.get_winning_odds(opposingMember);
        if (win_chance == null) throw new Error("Win odds are null")

        // "spin" the wheel to generate a random number
        console.log("");
        const random = await wheel_spin();

        // determine which team/member wins and the winning member's winning odds
        const team1Wins = win_chance > random;
        const winningTeam = (team1Wins ? this.teams[0] : this.teams[1]);
        const winningMember = (team1Wins ? member : opposingMember);
        const winningMemberOdds = team1Wins ? win_chance : 1-win_chance;
        const winningRange = team1Wins ? ("0-" + Math.round(win_chance*100)) : (Math.round(win_chance*100) + "-100")

        MENU.subcontest_correct_predictions += (subcontest_prediction == 0) == team1Wins ? 1 : 0;
        MENU.subcontest_predictions++;

        // show the winning member
        console.log("\n" + winningTeam.team_color + winningMember.name + COLORS.NORMAL + ` wins with ${Math.round(winningMemberOdds*100)}% odds (range ${winningRange})\n`);
        prompt("... continue");
        
        if (team1Wins) {
            this.scores[0]++;
            return;
        }

        this.scores[1]++;
    }

    /**
     * Start this match
     * @returns The winning team
     */
    public async start(): Promise<Team> {
        console.clear();
        MENU.draw();
        const match_prediction = this.predict_match_winner();

        // go through each sub-bracket and determine the outcome
        for (let [role, member] of this.teams[0].members) {
            console.clear();
            MENU.draw("Predicted winner: " + this.teams[match_prediction].print_name());

            const opposingMember = this.teams[1].members.get(BATTLE_OPPOSING_ROLE[role] as keyof typeof MEMBER_ROLES);
            if (opposingMember == undefined) throw new Error("Opposing member is undefined");
            
            await this.determine_subcontest(member, opposingMember);
        }

        const team1Wins = this.scores[0] > this.scores[1];
        
        // increment the match score predictions by 1 and the correct by 1 if we were correct
        MENU.match_correct_predictions += (match_prediction == 0) == team1Wins ? 1 : 0;
        MENU.match_predictions++;
        
        return team1Wins ? this.teams[0] : this.teams[1];
    }
}

export class Bracket {
    teams: Array<Team>;
    
    /**
     * Currently supports brackets with teams that have the same number of matches, ie 6 will not work.
     * @param teams 
     */
    constructor(teams: Array<Team>) {
        if (teams.length % 2 == 1) {
            throw new Error("Not implemented--Odd number of teams");
        }

        this.teams = teams;
    }

    public print() {
        let out = "";
        for (let i = 0; i < this.teams.length - 1; i += 2) {
            out += `${this.teams[i]?.print_name()} vs ${this.teams[i + 1]?.print_name()}\n`;
        }
        return out;
    }

    public async play_round() {
        console.clear();
        MENU.draw();
        console.log("Upcoming matches:\n" + this.print());
        prompt("... continue");

        const winners = [];

        // go through each team in pairs and put them against one another
        for (let i = 0; i < this.teams.length - 1; i += 2) {
            const match = new Match([this.teams[i] as Team, this.teams[i + 1] as Team]);
            const winner = await match.start();

            // we have a winner, add that to the list
            winners.push(winner);

            console.clear();
            MENU.draw();
            console.log(winner.print_name() + " win!");

            prompt("... continue");
        }

        return winners;
    }

    public async start() {
        do {
            // we have our list of winners, if we have more than 1 than we do another round
            const winners = await this.play_round();
            this.teams = winners;
        } while (this.teams.length > 1);

        // show the final results screen
        console.clear();
        MENU.draw();

        let match_percentage: string|number = Math.round(MENU.match_correct_predictions / MENU.match_predictions * 100);
        let subcontest_percentage: string|number = Math.round(MENU.subcontest_correct_predictions / MENU.subcontest_predictions * 100);

        console.log("Final results:");
        console.log(this.teams[0]?.print_name() + " wins this bracket\n");
        console.log(`You had ${match_percentage}% accuracy when predicting the outcomes of matches`);
        console.log(`You had ${subcontest_percentage}% accuracy when predicting the outcomes of subcontests in matches\n\n`);
    }
}