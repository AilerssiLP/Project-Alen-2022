package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Direction;

import sk.tuke.kpi.oop.game.characters.Armed;

import sk.tuke.kpi.oop.game.weapons.Fireable;
//import sk.tuke.kpi.oop.game.weapons.Firearm;

import java.util.Objects;

public class Fire<A extends Armed> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime) {
        setDone(true);

        if (getActor() == null)
        {return;
        }

        if(isDone() ==true)
        {

            Fireable fireable = getActor().getFirearm().fire();

            if (fireable == null) {
                return;

            }

            Direction direction = Direction.fromAngle(getActor().getAnimation().getRotation());

            Objects.requireNonNull(getActor().getScene()).addActor(fireable, getActor().getPosX() + 8, getActor().getPosY() + 8);

            new Move<Fireable>(direction, Float.MAX_VALUE).scheduleFor(fireable);


        }


    }

}
