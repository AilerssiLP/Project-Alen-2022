package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;


public class PowerSwitch extends AbstractActor{
    private Switchable switchable;
    //private Animation ovladac;


    public PowerSwitch(Switchable switchable) {
        this.switchable = switchable;
        //ovladac = new Animation("sprites/switch.png");
        //setAnimation(ovladac);
        setAnimation(new Animation("sprites/switch.png"));
    }

    public void switchOn() {
        if(switchable != null)
            switchable.turnOn();

        updateAnimation();
    }

    public void switchOff() {
        if(switchable != null)
            switchable.turnOff();
        updateAnimation();
    }

    public Switchable getDevice() {
        return switchable;
    }


    public void toggle(){
        if(switchable.isOn()){
            switchable.turnOff();
        }
        else{
            switchable.turnOn();
        }
        updateAnimation();
    }

    private void updateAnimation(){
        if(switchable != null && getAnimation() != null){
            if(switchable.isOn())
                getAnimation().setTint(Color.WHITE);
            else
                getAnimation().setTint(Color.GRAY);
        }
    }
}
