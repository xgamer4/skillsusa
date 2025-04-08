const TITLE = Bun.color("#a1a8a8", "ansi") + "Under" + Bun.color("#f21f1f", "ansi") + "Assault" + Bun.color("lightgray", "ansi") + " Sim"

export class Menu {
    subcontest_correct_predictions: number;
    subcontest_predictions: number;
    match_correct_predictions: number;
    match_predictions: number;
    
    constructor() {
        this.subcontest_correct_predictions = 0;
        this.subcontest_predictions = 0;
        this.match_correct_predictions = 0;
        this.match_predictions = 0;
    }

    public draw(status: string|null = null) {
        let match_percentage: string|number = Math.round(this.match_correct_predictions / this.match_predictions * 100);
        if (isNaN(match_percentage)) match_percentage = "-";

        let subcontest_percentage: string|number = Math.round(this.subcontest_correct_predictions / this.subcontest_predictions * 100);
        if (isNaN(subcontest_percentage)) subcontest_percentage = "-";

        // create the top nav bar with various info relating to the tournament
        const percentages = `Match Predictions: ${this.match_correct_predictions}/${this.match_predictions} (${match_percentage}%), Subcontest Predictions: ${this.subcontest_correct_predictions}/${this.subcontest_predictions} (${subcontest_percentage}%)`;
        console.log(TITLE + " | " + percentages + (status != null ? (" | " + status) : "") + "\n");
    }
}