package sk.tuke.kpi.oop.game.characters;

//import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.HashSet;
import java.util.Set;

public class Health
{

    private int Healthnow;

    private int Healthmax;

    @FunctionalInterface
    public interface ExhaustionEffect
    {
        void apply();
    }

    private Set<ExhaustionEffect> exhaustionEffect = new HashSet<>();

    public Health(int starthealth)
    {
        this.Healthmax = starthealth;

        this.Healthnow = starthealth;

    }
    public void onExhaustion(ExhaustionEffect effect) {


        exhaustionEffect.add(effect);

    }

    public Health(int starthealth, int ImaxHealth)
    {
        this.Healthnow = starthealth;

        this.Healthmax = ImaxHealth;
    }

    public void refill(int amount)//done
    {

        if(Healthnow < Healthmax - amount)
        {

            Healthnow =Healthnow+amount;

        }
        else {
            Healthnow = Healthmax;

        }

//        if (Healthnow == 0) {
//            for (Dying effect: dying) {
//                effect.apply();
//            }
//
//            dying.forEach(dying -> dying.apply());
//            dying.clear();
//        }

    }

    public void restore()//done
    {
        Healthnow =Healthmax;

    }
    public void exhaust()
    {
        Healthnow =0;

        if (Healthnow == 0)
        {

            for (ExhaustionEffect effect: exhaustionEffect)
            {
                effect.apply();

            }

            exhaustionEffect.forEach(dying -> dying.apply());

            exhaustionEffect.clear();


        }

    }

    public void drain(int amount)
    {

        if(amount <=0)
        {return;}

        if(Healthnow -amount> 0 )
        {
            Healthnow =Healthnow-amount;


        }
        else
        {
            Healthnow =0;

        }


        if (Healthnow == 0)
        {

            for (ExhaustionEffect effect: exhaustionEffect)
            {
                effect.apply();

            }

            exhaustionEffect.forEach(died -> died.apply());

            exhaustionEffect.clear();



        }

    }

    public int getValue()
    {

        return Healthnow;
    }

    public int getValueM()
    {

        return Healthmax;
    }


}
