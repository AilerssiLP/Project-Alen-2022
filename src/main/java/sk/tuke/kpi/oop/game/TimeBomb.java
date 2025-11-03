package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private float casdetonacie;
    private boolean check;
    public TimeBomb(float cas){
        this.casdetonacie = cas;
        check = false;
        setAnimation(new Animation("sprites/bomb.png"));
    }

    public boolean isActivated(){
        return check;
    }

    public void activate(){
        if(check){
            return;
        }

        check = true;
        setAnimation(new Animation("sprites/bomb_activated.png",16,16,0.1f, Animation.PlayMode.LOOP_PINGPONG));


        new ActionSequence<>(
            new Wait<>(casdetonacie),
            new Invoke<>(this::detonacia)
        ).scheduleFor(this);

    }

    public void detonacia(){
        setAnimation(new Animation("sprites/small_explosion.png",16,16,0.1f, Animation.PlayMode.ONCE));

        new When<>(
            () -> getAnimation().getCurrentFrameIndex() >= getAnimation().getFrameCount() - 1,
            new Invoke<>(() -> getScene().removeActor(this))
        ).scheduleFor(this);
    }
}
