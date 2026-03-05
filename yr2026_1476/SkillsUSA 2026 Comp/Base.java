public class Base {
    
    private Ownership owns;
    private String name;

    public Base(Ownership who, String name)
    {
        owns = who;
        this.name = name;
    }

    // Getter & Setter
    public Ownership whoOwns()
    {
        return owns;
    }
    public void setOwns(Ownership owns)
    {
        this.owns = owns;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        String str = " --- " + name + " --- \n";
        str += "Owned By: ";
        switch(owns)
        {
            case User:
                str += "Player.";
                break;
            case Opponent:
                str += "Enemy.";
                break;
            default:
                str += "Nobody.";
                break;
        }
        return str;
    }

}
