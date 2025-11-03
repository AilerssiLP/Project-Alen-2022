package sk.tuke.kpi.oop.game;

//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Scene;

public interface Movable extends Actor{
    int getSpeed();
    default void startedMoving(Direction direction){};
    default void stoppedMoving(){};
    default void collidedWithWall() {};
}
