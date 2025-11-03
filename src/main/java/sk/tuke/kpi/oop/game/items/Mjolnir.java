package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends Hammer {
    private int remainingUse = 4;;

    @Override
    public int getRemainingUses() {
        return remainingUse;
    }

    public Mjolnir() {
        this.setAnimation(new Animation("sprites/hammer.png"));
    }


    //@Override
    public void useWith(Reactor reactor) {
        if(reactor != null && reactor.repair()){
            remainingUse -= 1;
            super.useWith(reactor);
            if(remainingUse==0){
                Scene scene =getScene();
                scene.removeActor(this);
            }
        }
    }

}
