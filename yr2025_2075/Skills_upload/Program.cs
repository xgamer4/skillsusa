using System;
using System.Collections.Generic;
using System.Diagnostics.Eventing.Reader;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Console;

namespace SkillsUSA_Project
{
    internal class Program
    {
        static void Main(string[] args)
        {
            D_DPS.Player_stats();
            D_Support.Player_stats();
            D_Tank.Player_stats();
            WriteLine("\n");

            F_DPS.Player_stats();
            F_Support.Player_stats();
            F_Tank.Player_stats();
            WriteLine("\n");

            O_DPS.Player_stats();
            O_Support.Player_stats();
            O_Tank.Player_stats();
            WriteLine("\n");

            S_DPS.Player_stats();
            S_Support.Player_stats();
            S_Tank.Player_stats();
            WriteLine("\n");

            Brackets.DetermineBrackets();
            Brackets.PlayerGuess();
        }
    }

    class MyRandom
    {
        static internal Random random = new Random();
    }

    class Dynasty: MyRandom
    {
        
    }

    class D_DPS : Dynasty
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(50, 101);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Dynasty: DPS");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class D_Support : Dynasty
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(50, 101);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Dynasty: Support");
            WriteLine(dps_proficiency +"\tDPS skills");
            WriteLine(support_proficiency +"\tSupport skills");
            WriteLine(tank_proficiency +"\tTank skills\n");
        }
    }
    class D_Tank : Dynasty
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(50, 101);
            WriteLine("Dynasty: Tank");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }



    class Fuel: MyRandom
    {
        
    }

    class F_DPS : Fuel
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(50, 101);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Fuel: DPS");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class F_Support : Fuel
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(50, 101);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Fuel: Support");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class F_Tank : Fuel
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(50, 101);
            WriteLine("Fuel: Tank");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }



    class Outlaws: MyRandom
    {
        
    }

    class O_DPS: Outlaws
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(50, 101);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Outlaws: DPS");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class O_Support : Outlaws
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(50, 101);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Outlaws: Support");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class O_Tank : Outlaws
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(50, 101);
            WriteLine("Outlaws: Tank");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }



    class Shock : MyRandom
    {
        
    }

    class S_DPS: Shock
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(50, 101);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Shock: DPS");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class S_Support : Shock
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(50, 101);
            int tank_proficiency = random.Next(1, 76);
            WriteLine("Shock: Support");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }
    class S_Tank : Shock
    {
        static internal void Player_stats()
        {
            int dps_proficiency = random.Next(1, 76);
            int support_proficiency = random.Next(1, 76);
            int tank_proficiency = random.Next(50, 101);
            WriteLine("Shock: Tank");
            WriteLine(dps_proficiency + "\tDPS skills");
            WriteLine(support_proficiency + "\tSupport skills");
            WriteLine(tank_proficiency + "\tTank skills\n");
        }
    }





    class Brackets: MyRandom
    {
        static string[] brackets = new string[4];
        static string player_guess1;
        static string player_guess2;
        static string player_guess3;

        static internal void DetermineBrackets()
        {
            int findBracket = random.Next(1, 4);

            string dynasty = "Dynasty";
            string fuel = "Fuel";
            string outlaws = "Outlaws";
            string shock = "Shock";

            brackets[0] = dynasty;
            if (findBracket == 1)
            {
                brackets[1] = fuel;
                brackets[2] = outlaws;
                brackets[3] = shock;
            }
            else if (findBracket == 2)
            {
                brackets[1] = outlaws;
                brackets[2] = fuel;
                brackets[3] = shock;
            }
            else
            {
                brackets[1] = shock;
                brackets[2] = fuel;
                brackets[3] = outlaws;
            }

            WriteLine("The brackets are: {0} vs {1} and {2} vs {3}", brackets[0], brackets[1], brackets[2],brackets[3]);
        }
        static internal void PlayerGuess()
        {
            WriteLine("Who do you guess will likely win between {0} and {1}?", brackets[0], brackets[1]);
            player_guess1 = ReadLine();


            WriteLine("Who do you guess will likely win between {0} and {1}?", brackets[2], brackets[3]);
            player_guess2 = ReadLine();

            WriteLine("Who do you think will win between the final contestents, {0} and {1}", player_guess1, player_guess2);
            player_guess3 = ReadLine();
        }

        static internal void AMatch()
        {

            int team1_wins = 0;
            int team2_wins = 0;
            for(int game = 0; game < 3; game++)
            {

            }
        }
    }
}
