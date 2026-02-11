import { MEMBER_ROLES, NAMES } from "./globals.ts";

export class Member {
    name: string;
    role: keyof typeof MEMBER_ROLES;
    proficiencies: Map<keyof typeof MEMBER_ROLES, number>;
    
    constructor(role: keyof typeof MEMBER_ROLES) {
        this.role = role;
        this.proficiencies = new Map();
        this.name = "";
        
        this.make_name();
        this.make_proficiencies();
    }

    private make_name() {
        // choose this player's name from the list of names
        this.name = NAMES[Math.floor(Math.random() * NAMES.length)] as string
    }

    /**
     * Make the random proficiencies of this member
     */
    private make_proficiencies() {
        for (const key of Object.keys(MEMBER_ROLES)) {
            this.proficiencies.set(key as keyof typeof MEMBER_ROLES, Math.floor(Math.random() * 101));
        }
    }

    /**
     * Get the odds of winning a duel against another member
     * @param other 
     * @returns 
     */
    public get_winning_odds(other: Member): number|null {
        if (!this.proficiencies.has(this.role) || !other.proficiencies.has(other.role)) {
            return null;
        }

        const this_proficiency = this.proficiencies.get(this.role);
        if (this_proficiency == undefined) return null;

        const other_proficiency = other.proficiencies.get(other.role);
        if (other_proficiency == undefined) return null;

        return this_proficiency / (this_proficiency + other_proficiency);
    }
}