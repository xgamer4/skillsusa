import java.util.Scanner;

public class UserController extends Player {
    private Scanner scanner = new Scanner(System.in);

    public UserController(Game game) {
        super(game, new ActionManager());
        super.playerNumber = 1;
    }

    public String getAction(){
        if(actionManager.getPlayer() == null){
            actionManager.setPlayer(this);
        }
        do{  
            char input = scanner.next().charAt(0);
            switch(input){
                case 'q':
                    return "quit";
                case 'a':
                    return actionManager.Attack();
                case 't':
                    return actionManager.Train();
                case 'e':
                    return actionManager.Expand();
            }
        }
        while(true);
    }

    public int getBases(){
        return bases;
    }

    public int getUnits(){
        return units;
    }
}
