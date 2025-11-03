package sk.tuke.kpi.oop.game.characters;

//import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
//import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;
//import sk.tuke.kpi.oop.game.items.Hammer;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed
{
    private int energy ;

    private int ammo ;

    private Firearm gun;

    private Backpack backpack;

    private Health health;

    public static Topic<Ripley> RIPLEY_DIED = new Topic<>("Ripley died", Ripley.class);

    public Ripley()
    {

        super("Ailerko");

        energy = 100;// stare

        ammo = 50;

        gun = new Gun(ammo, 225);

        health =new Health(energy);

        backpack = new Backpack("Baggieee",10);

        setAnimation(new Animation("sprites/player.png",32,32,0.1f,
            Animation.PlayMode.LOOP_PINGPONG));

        getAnimation().stop();

        if(energy == 0){
            setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
        }



        getAnimation().pause();

        health.onExhaustion(() -> {
            setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));

                Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
            getScene().cancelActions(this);

        }

        );

    }

    @Override
    public int getSpeed()
    {

        return 3;

    }

    @Override
    public void startedMoving(Direction direction)
    {

        getAnimation().setRotation(direction.getAngle());

        getAnimation().play();}

    @Override
    public void stoppedMoving()
    {

        getAnimation().stop();

    }

    //public void setEnergy(int energy) {
    //    this.energy = energy;
    //}

    //public int getEnergy(){
    //    return energy;
    //}

    public int getAmmo()
    {

        return ammo;

    }

    public void setAmmo(int ammo)
    {

        this.ammo = ammo;

    }

    @Override
    public Backpack getBackpack()
    {

        return backpack;

    }

    @Override
    public Health getHealth()
    {

        return health;

    }

    @Override
    public Firearm getFirearm()
    {

        return gun;

    }

    @Override
    public void setFirearm(Firearm weapon)
    {

        gun =weapon;

    }

    public void draine()
    {
        getHealth().drain(1);
    }

    public void showRipleyState()
    {

        if(energy == 0)
        {

            setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));

        }


        int windowHeight = Objects.requireNonNull(getScene()).getGame().getWindowSetup().getHeight();


        int offset = GameApplication.STATUS_LINE_OFFSET;


        getScene().getGame().getOverlay().drawText("Health " +health.getValue() + "  " +
            "Your Ammo " + getFirearm().getAmmo() + "  " +
            "Maximum Ammo " + getFirearm().getAmmoMax(), 100, windowHeight - offset);
    }


}
