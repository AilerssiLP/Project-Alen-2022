package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    //Animation heliAnimation;
    private boolean isRunning=false;

    public Helicopter(){
        setAnimation(new Animation("sprites/heli.png",64,64,.1f,
            Animation.PlayMode.LOOP));

    }

    public void searchAndDestroy(){

        if(!isRunning){

            new ActionSequence<>(
                new Loop<>(new Invoke<>(this::searchAndDestroy))
            ).scheduleFor(this);
            isRunning=true;

            //new ActionSequence<>(new Wait<>(10),new Invoke(() -> this.isRunning= false)).scheduleFor(this);
            //new Loop<>(new Invoke<Helicopter>(this::searchAndDestroy)).scheduleFor(this);
        }


        Player player =(Player)getScene().getFirstActorByType(Player.class);


        int x = this.getPosX() - player.getPosX();

        int y = this.getPosY() - player.getPosY();


        if(x<-1){
            x=1;

        }

        if(y>1){
            y=-1;

        }

        if(x>1){
            x=-1;

        }

        if(y<-1) {
            y = 1;

        }

        this.setPosition((this.getPosX()+x),(this.getPosY()+y));


        if(this.intersects(player)){
            player.setEnergy(player.getEnergy()-1);

            if(player.getEnergy()==0){
                player.setEnergy(0);

            }

        }

    }

}
