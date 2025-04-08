public class TeamMember {
    // Integer values (between 0 and 100) representing the member's proficiency in each role
    private int dpsProficiency;
    private int tankProficiency;
    private int supportProficiency;

    /**
     * setDpsProficiency is a setter function that ensures that the DPS proficiency is set to a valid integer value
     * ranging from 0 to 100.
     * @param proficiency the DPS proficiency to set to the specific team member
     */
    public void setDpsProficiency(int proficiency) {
        dpsProficiency = Math.clamp(proficiency, 0, 100);
    }

    /**
     * getDpsProficiency tells the caller the DPS proficiency level of the specified team member.
     */
    public int getDpsProficiency() {
        return dpsProficiency;
    }

    /**
     * setTankProficiency is a setter function that ensures that the tank proficiency is set to a valid integer value
     * ranging from 0 to 100.
     * @param proficiency the tank proficiency to set to the specific team member
     */
    public void setTankProficiency(int proficiency) {
        tankProficiency = Math.clamp(proficiency, 0, 100);
    }

    /**
     * getTankProficiency tells the caller the tank proficiency level of the specified team member.
     */
    public int getTankProficiency() {
        return tankProficiency;
    }

    /**
     * setSupportProficiency is a setter function that ensures that the support proficiency is set to a valid integer
     * value ranging from 0 to 100.
     * @param proficiency the support proficiency to set to the specific team member
     */
    public void setSupportProficiency(int proficiency) {
        supportProficiency = Math.clamp(proficiency, 0, 100);
    }

    /**
     * getSupportProficiency tells the caller the DPS proficiency level of the specified team member.
     */
    public int getSupportProficiency() {
        return supportProficiency;
    }

    @Override
    public String toString() {
        return "dps_proficiency = " + getDpsProficiency() + ", tank_proficiency = " + getTankProficiency() + ", support_proficiency = " + getSupportProficiency();
    }
}
