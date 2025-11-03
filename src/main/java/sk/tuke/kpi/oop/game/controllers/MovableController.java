package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
//import sk.tuke.kpi.oop.game.characters.Health;
//import sk.tuke.kpi.oop.game.Scene;

import java.util.*;

public class MovableController implements KeyboardListener
{
    private Movable actor;

    private Move<Movable> move =null;

    private Input.Key key1 ;



    private Set<Input.Key> Keys = new LinkedHashSet<>();
    //

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries
        (
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)

    );//cviko



    public MovableController(Movable actor)
    {
        this.actor =actor;

    }//cviko

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {

        if (keyDirectionMap.containsKey(key) )
        {


            if (move != null)
            {
                move.stop();

            }

            if(key1==null)
            {
                key1 = key;

            }

            Keys.add(key);

            //List<Input.Key> keys = new ArrayList<>(Keys);

            List<Input.Key> list = new ArrayList<>(Keys);

            Direction direction = Direction.NONE;

            for (Input.Key value: list)
            {

                direction = direction.combine(keyDirectionMap.get(value));

            }

            move =new Move<Movable>(direction, Short.MAX_VALUE);

            move.scheduleFor(actor);

        }

    }//cviko

    @Override
    public void keyReleased(@NotNull Input.Key key)
    {
        if(key1==null)
        {
            key1 = key;

        }

        if (keyDirectionMap.containsKey(key))
        {
            Keys.remove(key);

            if (move != null)
            {
                if (Keys.isEmpty())
                {
                    move.stop();

                    move =null;

                } else
                {

                    move.stop();

                    //List<Input.Key> keys = new ArrayList<>(Keys);

                    List<Input.Key> list =new ArrayList<Input.Key>(Keys);


                    Direction direction = Direction.NONE;

                    for (Input.Key value: list)
                    {
                        direction =direction.combine(keyDirectionMap.get(value));

                    }

                    move =new Move<Movable>(direction, Short.MAX_VALUE);

                    move.scheduleFor(actor);

                }

            }

        }

    }

}
