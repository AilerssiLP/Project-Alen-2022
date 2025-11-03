package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
//import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;
//import sk.tuke.kpi.gamelib.map.EmptyMap;
import sk.tuke.kpi.gamelib.map.SceneMap;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
//import sk.tuke.kpi.oop.game.characters.Ripley;


public class Move<A extends Movable> implements Action<A> {
    private A actor;

    private Direction direction;

    private float time;

    private boolean done;

    //private Scene scene;

    private float duration;

    private boolean castfirst ;

    //public Move(Direction direction) {
    //    this(direction,100000);
    //}

    public Move(Direction direction, float duration)
    {
        this.direction = direction;

        time = 0;

        done = false;

        castfirst = false;

        this.duration = duration;

    }



    @Override
    public @Nullable A getActor()
    {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor)
    {
        this.actor=actor;
    }

    @Override
    public boolean isDone()
    {
        return done;
    }
    public Direction getDirection()
    {return direction;}

    @Override
    public void execute(float deltaTime)
    {
//        if(this.castfirst =true){
//            this.castfirst =false;
//            this.actor.startedMoving(this.direction);
//
//        }else{
//            this.time =this.time + deltaTime;
//        }
        if (getActor() == null || isDone()) {

            return;

        }

        if(!this.castfirst){

            this.castfirst =true;

            this.actor.startedMoving(this.direction);

        }

        this.time =this.time + deltaTime;


        int newY = this.actor.getPosition().getY() + this.actor.getSpeed() * this.direction.getDy();

        int newX = this.actor.getPosition().getX() + this.actor.getSpeed() * this.direction.getDx();

        this.actor.setPosition(newX, newY);

        SceneMap sceneMap = actor.getScene().getMap();


        if(sceneMap.intersectsWithWall(actor)) {

            final int posX = actor.getPosX() - (direction.getDx() * actor.getSpeed());

            final int posY = actor.getPosY() - (direction.getDy() * actor.getSpeed());

            actor.collidedWithWall();

            actor.setPosition(posX, posY);

            actor.stoppedMoving();

        }
        //if(scene.getMap().intersectsWithWall(actor))
        if(this.done || this.time - this.duration >= 1e-5 )
        {
//            this.actor.stoppedMoving();
//            this.done = true;

            stop();

        }
    }


    @Override
    public void reset()
    {
        this.done =false;

        time = 0;

        this.castfirst =false;

    }

    /*
    public Move(Direction direction) {
        this.done = false;
        this.direction = direction;
        this.duration = 10000;
        this.time = 0;
        this.castfirst = true;
    }
    */

    public void stop()
    {
        //time = 0;
        //castfirst = true;
        if (getActor() == null) {

            return;

        }

        done = true;

        actor.stoppedMoving();

    }

    public Move(Direction direction) {
        this(direction, 0);

    }


}
