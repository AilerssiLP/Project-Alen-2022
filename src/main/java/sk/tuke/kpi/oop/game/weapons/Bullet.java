package sk.tuke.kpi.oop.game.weapons;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;
//import sk.tuke.kpi.oop.game.characters.Ripley;


public class Bullet extends AbstractActor implements Fireable
{
    private final int damage;

    public Bullet()
    {

        setAnimation(new Animation("sprites/bullet.png", 16, 16, 0.1f));

        damage =20;

    }

    ///////////////        ///////////////        ///////////////        ///////////////
    @Override
    public int getSpeed()
    {
        return 7;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        if(direction !=Direction.NONE)
        {

            getAnimation().setRotation(direction.getAngle());

        }

    }

    @Override
    public void collidedWithWall()
    {

        Objects.requireNonNull(getScene()).removeActor(this);

    }
    ///////////////        ///////////////        ///////////////        ///////////////

    @Override
    public void addedToScene(@NotNull Scene scene) {

        super.addedToScene(scene);

        new Loop<>(
            new Invoke<>(this::collision)).scheduleFor(this);


    }

    private void collision()
    {
        if (getScene() == null)
        {return;}


        for (Actor actor: getScene().getActors())
        {

            if (actor instanceof Alive ==true && intersects(actor)  )
            {

                ((Alive) actor).getHealth().drain(damage);


                collidedWithWall();

            }


        }

    }

    ///////////////        ///////////////        ///////////////

}
