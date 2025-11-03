package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private boolean power;
    public Computer(){
        setAnimation(new Animation("sprites/computer.png",
            80,48,.2f,
            Animation.PlayMode.LOOP));
        setPowered(false);
    }
    public int add(int x, int y){
        if(!power) {
            return 0;
        }

        return x+y;
    }
    public float add(float x, float y){
        if(!power) {
            return 0;
        }


        return x+y;
    }

    public int sub(int x, int y){
        if(!power) {
            return 0;
        }
        return x-y;
    }
    public float sub(float x, float y){
        if(!power) {
            return 0;
        }
        return x-y;
    }

    @Override
    public void setPowered(boolean on) {
        power = on;

        if(!power){
            getAnimation().pause();
        } else {
            getAnimation().play();
        }
    }
}
