/**
 * A class that contains a group of players playing for a team
 * it also defines which players play which roles and keeps track
 * of those roles
 */
public class Team {
    String name;
    Player[] players;
    int[] roles;
    
    /**
     * Constructor takes in a name for the team, and creates the players
     * @param name
     */
    public Team(String name){
        players = new Player[3];
        this.name = name;
        /**
         * creates players for the team
         */
        for(int i = 0; i < 3; i++){
            players[i] = new Player();
        }

        players[0].setRole(Player.Role.tank);
        players[1].setRole(Player.Role.dps);
        players[2].setRole(Player.Role.support);
    }

    // these methods return the player for the given role
    public Player getTankPlayer(){
        for (Player player : players) {
            if(player.role == Player.Role.tank)
                return player;
        }
        return null;
    }
    public Player getDPSPlayer(){
        for (Player player : players) {
            if(player.role == Player.Role.dps)
                return player;
        }
        return null;
    }
    public Player getSupportPlayer(){
        for (Player player : players) {
            if(player.role == Player.Role.support)
                return player;
        }
        return null;
    }
}
