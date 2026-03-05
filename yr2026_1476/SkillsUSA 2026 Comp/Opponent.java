import java.util.*;

public class Opponent {

    public boolean isDone;
    public String name;

    private ArrayList<Base> basesOwned;
    private int troopAmount;

    public Opponent(String name)
    {
        this.name = name;
        basesOwned = new ArrayList<Base>();
    }

    public void updateBases(ArrayList<Base> allBases)
    {
        basesOwned.clear();
        for (Base base : allBases) {
            if(base.whoOwns() == Ownership.Opponent)
            {
                basesOwned.add(base);
            }
        }
    }


    public void setTroops(int newTroops)
    {
        troopAmount = newTroops;
    }
    public int getTroops()
    {
        return troopAmount;
    }
    public void killTroops(int amount)
    {
        troopAmount = troopAmount - amount < 0 ? 0 : troopAmount - amount;
    }
    public void trainTroops(int amount)
    {
        troopAmount += amount;
    }
    
    public int getBaseAmount()
    {
        return basesOwned.size();
    }
    public ArrayList<Base> getBases()
    {
        return basesOwned;
    }

    public void loseBase()
    {
        if(basesOwned.size() > 0)
        {
        basesOwned.get(0).setOwns(Ownership.User);
        }
    }

    public int[] attackDice()
    {
        if(troopAmount > 1)
        {
        int troopsWith = troopAmount - 1;
        Random rand = new Random();
        int[] rolls = new int[troopsWith];
        for(int i = 0; i < troopsWith; i++)
        {
            rolls[i] = rand.nextInt(6) + 1;
        }

        boolean isSorted = false;
        while(!isSorted)
        {
            isSorted = true;
            for(int i = 0; i < rolls.length -1; i++)
            {
                if(rolls[i] < rolls[i+1])
                {
                    isSorted = false;
                    int temp = rolls[i];
                    rolls[i] = rolls[i+1];
                    rolls[i+1] = temp;
                }
            }
        }

        return rolls;
        }
        else {
            return new int[] {0};
        }
    }
    public int[] defendDice()
    {
        if(troopAmount > 0)
        {
        int troopsWith = troopAmount;
        Random rand = new Random();
        int[] rolls = new int[troopsWith];
        for(int i = 0; i < troopsWith; i++)
        {
            rolls[i] = rand.nextInt(6) + 1;
        }

        boolean isSorted = false;
        while(!isSorted)
        {
            isSorted = true;
            for(int i = 0; i < rolls.length -1; i++)
            {
                if(rolls[i] < rolls[i+1])
                {
                    isSorted = false;
                    int temp = rolls[i];
                    rolls[i] = rolls[i+1];
                    rolls[i+1] = temp;
                }
            }
        }

        return rolls;
        }
        else {
            return new int[] {0};
        }
    }


    @Override
    public String toString()
    {
        String str = "USER " + name + ":\n";
        str += "Bases owned: " + basesOwned.size();
        str += "\nTroops owned: " + troopAmount;
        return str;
    }
    
}
