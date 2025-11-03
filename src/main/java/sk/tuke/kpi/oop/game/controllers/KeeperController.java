package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
//import sk.tuke.kpi.oop.game.Direction;
//import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

//import java.util.Map;

//import static sk.tuke.kpi.gamelib.Input.Key.*;

public class KeeperController implements KeyboardListener
{

    private Keeper keeper;

    public KeeperController(Keeper keeper)
    {
        this.keeper = keeper;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if(key == Input.Key.ENTER)
        {

            new Take<>().scheduleFor(keeper);

        }else if(key == Input.Key.BACKSPACE)
        {

            new Drop<>().scheduleFor(keeper);

        }else if(key == Input.Key.S)
        {

            new Shift<>().scheduleFor(keeper);

        }else if(key == Input.Key.U)
        {

            fillerU();

        }else if(key == Input.Key.B)
        {

            fillerB();

        }

    }
    private void fillerU(){

        Usable<?> usable = Objects.requireNonNull(keeper.getScene()).getActors().stream()
            .filter(Usable.class::isInstance).filter(keeper::intersects)
            .map(Usable.class::cast).findFirst().orElse(null);


        if (usable != null)
        {

            new Use<>(usable).scheduleForIntersectingWith(keeper);

        }

    }
    private void fillerB(){
        if (keeper.getBackpack().getSize() == 0)
        {

            return;

        }

        Collectible things;

        try
        {
            things = keeper.getBackpack().peek();

        } catch (Exception e)
        {
            things = null;

        }

        if (things instanceof Usable && things instanceof Collectible )
        {
            @SuppressWarnings("unchecked")

            Use<Actor> action = new Use<>((Usable<Actor>) things);


            action.scheduleForIntersectingWith(keeper);

        }

    }

}
