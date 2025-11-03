package sk.tuke.kpi.oop.game.behaviours;


import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;


public class RandomlyMoving implements Behaviour<Movable>
{
    private Movable movable = null;


    @Override
    public void setUp(Movable action)
    {
        movable = action;

        if (action==null )
        {
            return;

        }

        new Loop<>(
                new ActionSequence<>(
                   new Invoke<>(this::makeMove), new Wait<>(1)
                )).scheduleFor(movable);

//        } else {
//
//        }

    }

    private void makeMove(Movable actor)
    {
        Direction direction =null;

        if (movable == null)
        {return;}

        int max =3;

        int min =1;

        int dur =4;


        int X =(int) (Math.random() * (max)) - min;

        int Y =(int) (Math.random() * (max)) - min;



        for (Direction value : Direction.values())
        {
            if (Y ==value.getDy() &&  X ==value.getDx() )
            {
                direction =value;

            }

        }


        long duration = (int) (Math.random() * (dur)) - min;

        assert direction != null;

        actor.getAnimation().setRotation(direction.getAngle());


        Move<Movable> action = new Move<>(direction, duration);


        action.scheduleFor(actor);

    }

}

