// This class is used for finding who owns which base using the "claimed" string.
public class Base
{
    public String claimed;

    public Base(String claimed)
    {
        this.claimed = claimed;
    }

    public String getClaimed()
    {
        return claimed;
    }
}
