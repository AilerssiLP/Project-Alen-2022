package sk.tuke.kpi.oop.game;
//
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.HashSet;
import java.util.Set;


public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
    private int result1=0;
    //private EnergyConsumer devices;
    private Set<EnergyConsumer> device;
    //private boolean used;
    private boolean running;
    private Animation normalAnimation;
    private Animation overheatedAnimation;
    private Animation brokenAnimation;
    //private Animation offAnimation;
    //private Animation TurnedOffAnimation;


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        PerpetualReactorHeating perpetualReactorHeating= new PerpetualReactorHeating(1);

        perpetualReactorHeating.scheduleFor(this);
        this.device = new HashSet<>();
    }

    public void turnOn(){
        running = true;

        for(EnergyConsumer computer: device) {
            computer.setPowered(true);

        }

        for(EnergyConsumer light: device) {
            light.setPowered(true);

        }


        updateAnimation();
    }

    public void turnOff(){
        running = false;

        for(EnergyConsumer computer: device) {
            computer.setPowered(false);

        }

        for(EnergyConsumer light: device) {
            light.setPowered(false);

        }

        updateAnimation();
    }

    public boolean isOn(){
        return running;
    }

    public boolean extinguish(){
        if(damage >=100){
            if(temperature>4000){
                temperature=4000;
                setAnimation(new Animation("sprites/reactor_extinguished.png"));
                return true;
            } else return false;
        } else return false;
    }

    public boolean repair(){
        //boolean result = false;// toto bolo v repair
        //if(damage==0){
        //    return false;
        //}
        update();
        if(damage < 100 && damage > 0){
            if(damage > 33 && damage <=66  && damage >0){
                temperature = ((damage * 40)+(damage * 20)) +2000 ;// ((((damage*2)*40)/21)+2000);
            }else{
                //if(damage > 66 ){//posledne
                //    temperature= (damage*80)+2000 ;
                //}else{
                temperature = (damage * 40) +2000 ;
                //}
            }
        }

        if(result1==1){
            updateAnimation();
            return true;
        }
        updateAnimation();
        return false;
    }
    /*public void repairWith(Hammer hammer, Mjolnir mjolnir){
        if(hammer!=null && hammer.getRemainingUses()>0 && damage <100){
            if(damage>=50){
                damage -=50;
            }else {
                damage= 0;
            }
            hammer.use();
        }
        if (mjolnir!=null && mjolnir.getRemainingUses() > 0  && damage <100) {
            if (damage >= 50) {
                damage -= 50;
            } else {
                damage = 0;
            }
            mjolnir.use();
        }
        if(damage<100){
            if(damage > 33 && damage <66 ){
                temperature = ((damage * 20)+(damage * 20)/2) +4000 ;// ((((damage*2)*40)/21)+2000);
            }else{
                if(damage > 66 ){
                    temperature= (damage*40)+4000 ;
                }else{
                temperature = (damage * 20) +4000 ;
                }
            }
        }
        //hammerworks =1;
        updateAnimation();
    }*/

    // alt + insert
    public Reactor() {
        temperature =0;
        damage =0;
        normalAnimation = new Animation("sprites/reactor_on.png",
            80,80,.1f,
            Animation.PlayMode.LOOP_PINGPONG);  // ctrl +q, ctrl + space
        setAnimation(normalAnimation);
        overheatedAnimation = new Animation("sprites/reactor_hot.png",
            80,80,.05f,
            Animation.PlayMode.LOOP_PINGPONG);  // ctrl +q, ctrl + space
        setAnimation(normalAnimation);
        brokenAnimation = new Animation("sprites/reactor_broken.png",
            80,80,.05f,
            Animation.PlayMode.LOOP_PINGPONG);  // ctrl +q, ctrl + space
        setAnimation(normalAnimation);
        updateAnimation();
    };

    public int getTemperature(){
        return temperature;
    }

    public int getDamage(){
        return damage;
    }

    public void increaseTemperature(int increment){
        if(isOn() && increment>0 && temperature<6000){
            if(damage > 33 && damage<66) {
                temperature += (increment+ increment/2);//
            }else {
                if(damage>=66){
                    temperature += (increment+ increment);
                }else{
                    temperature += increment;
                }
            }

        }
        if(temperature > 4000){
            damage = (temperature - 4000) / 20;

        }
        updateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if(isOn() && damage <100 && decrement>0 && temperature>0){
            if(damage >= 50) {
                temperature -= decrement/2;
            }else {
                temperature -= decrement;
            }
        }
        if(temperature<0){
            temperature=0;
        }
        updateAnimation();
    }

    private void updateAnimation(){
        if (isOn()){
            //EnergyConsumer device = this.device;
            //device.setPowered(isOn());
            if(temperature <= 4000){
                normalAnimation.setFrameDuration(0.1f-(this.damage/3000f));
                setAnimation(normalAnimation);
            }
            if(temperature>4000) { // animation
                overheatedAnimation.setFrameDuration(0.05f-(this.damage/3000f));
                setAnimation(overheatedAnimation);
                if(temperature>=6000 && damage >=100){
                    damage=100;
                    setAnimation(brokenAnimation);
                    running=false;
                    setter();
                }
            }
            else{
                setAnimation(normalAnimation);
            }
        }else if(!isOn() && damage<100){
            //if(){//>=
            //temperature=6000;
            setAnimation(new Animation("sprites/reactor.png"));
            //}
        }

    }

    private void setter(){
        for(EnergyConsumer computer: device) {
            computer.setPowered(false);

        }

        for(EnergyConsumer light: device) {
            light.setPowered(false);

        }
    }

    public void addDevice(EnergyConsumer device){
        if(device!=null && !this.device.contains(device)){
            //if() {
            device.setPowered(isOn());
            this.device.add(device);
            //}
            //used =true;
        }
    }
    public void removeDevice(EnergyConsumer device){
        if(this.device!=null && this.device.contains(device)){
            //if() {
            device.setPowered(false);
            this.device.remove(device);
            //}
            //used = false;
        }
    }
    private void update(){
        if(damage < 100 && damage > 0){
            if(damage>=50){
                damage -=50;
            }else {
                damage -=50;
                temperature =  2000 + (damage * 40);
                damage = 0;
                //temperature=4000;//posl
            }
            result1 = 1;
        }


    }
}

