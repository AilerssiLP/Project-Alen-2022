package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.List;

public class Alien extends AbstractActor implements Movable,Enemy,Alive {

    private Behaviour<? super Alien> behaviour;

    private Health health;

    public Alien() {

        setAnimation(new Animation("sprites/alien.png",32,32,0.1f,
            Animation.PlayMode.LOOP_PINGPONG));

        getAnimation().stop();

        health = new Health(100, 100);

        health.onExhaustion(() -> getScene().
            removeActor(this));


    }

    public Alien(int healthValue, Behaviour<? super Alien> behaviour) {

        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        health = new Health(healthValue, 100);

        this.behaviour = behaviour;

        health.onExhaustion(() -> {getAnimation().stop();
        getScene().
            removeActor(this);}
        );


    }


    @Override
    public int getSpeed()
    {
        return 2;
    }

    @Override
    public Health getHealth()
    {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {

        super.addedToScene(scene);

        if (behaviour != null)
        {
            //  return;
            // } else {

            behaviour.setUp(this);
        }

        new Loop<>((
            new Invoke<>(this::draining)
        )).scheduleFor(this);

    }

    private void draining()
    {

        if (getScene() == null)
        {
            return;

        }

        List<Actor> alive = getScene().getActors();

        for (Actor aliveActor : alive)
        {
            if (aliveActor instanceof Alive && (aliveActor instanceof Enemy)==false && this.intersects(aliveActor))
            {

                ((Alive) aliveActor).getHealth().drain(1);

                break;

            }

        }

    }



}
