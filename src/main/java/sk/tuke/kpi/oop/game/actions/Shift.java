package sk.tuke.kpi.oop.game.actions;

//import sk.tuke.kpi.gamelib.Actor;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;

public class Shift<K extends Keeper> extends AbstractAction<K>
{//done

    @Override
    public void execute(float deltaTime)
    {
        if( isDone() || getActor() == null || getActor().getBackpack().peek()==null)
        {
            setDone(true);

            return;

        }

        getActor().getBackpack().shift();

        setDone(true);

    }


}
