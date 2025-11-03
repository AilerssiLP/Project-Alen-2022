package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Actor> {
    private Hammer hammer;

    public Locker() {

        setAnimation(new Animation("sprites/locker.png", 16, 16));

    }
    ///////////////        ///////////////        ///////////////

    @Override
    public void useWith(Actor actor)
    {

        getScene().addActor(hammer, actor.getPosX(), actor.getPosY());

    }

    ///////////////        ///////////////        ///////////////        ///////////////
    @Override
    public Class<Actor> getUsingActorClass()
    {return Actor.class;}
}
