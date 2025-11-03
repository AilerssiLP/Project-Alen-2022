package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    //private Reactor reactor;
    private Animation onAnimation;
    private Animation offAnimation;

    private boolean on=false;;
    private boolean powered=false;;

    public Light(){
        //on=false;
        //powered=
        offAnimation= new Animation("sprites/light_off.png");
        onAnimation = new Animation ("sprites/light_on.png");
        setAnimation(offAnimation);
    }

    public void turnOn(){
        this.on=true;
        if(this.powered && this.on){
            setAnimation(onAnimation);
        }else {
            setAnimation(offAnimation);
        }
    }

    public void turnOff(){
        this.on=false;
        if(this.powered && this.on){
            setAnimation(onAnimation);
        }else {
            setAnimation(offAnimation);
        }
    }

    public boolean isOn(){
        return this.on;
    }

    public void toggle(){
        if(this.on){
            this.on = false;
        }else{
            this.on=true;}
        if(this.powered && this.on){
            setAnimation(onAnimation);
        }else {
            setAnimation(offAnimation);
        }
    }

    public void setPowered(boolean powered){
        this.powered = powered;

        if(this.powered && this.on){
            setAnimation(onAnimation);
        }else {
            setAnimation(offAnimation);
        }
    }
}
