import { MEMBER_ROLES, COLORS } from "./globals.ts";
import { Member } from "./member.ts";

export class Team {
    name: string;
    members: Map<keyof typeof MEMBER_ROLES, Member>;
    team_color: string;

    constructor(name: string, color: string = "") {
        this.name = name;
        this.members = new Map();
        this.make_members();

        // use our own team color or use the default one from COLORS
        if (color == "") color = COLORS.TEAM as string;
        this.team_color = color;
    }

    public print_name() {
        return `${this.team_color}${this.name}${COLORS.NORMAL}`;
    }

    /**
     * Make the members of this team with random proficiencies
     */
    private make_members() {
        for (const [key, value] of Object.entries(MEMBER_ROLES)) {
            this.members.set(key as keyof typeof MEMBER_ROLES, new Member(key as keyof typeof MEMBER_ROLES));
        }
    }
}