package sk.tuke.kpi.oop.game.actions;

//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;

//import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<K extends Keeper> extends AbstractAction<K>
{//working


    @Override
    public void execute(float deltaTime)
    {
        setDone(true);

        if (getActor()==null)
        {
            return;

        }

        if (getActor().getBackpack().peek()==null || getActor().getScene()==null) {

            return;

        }

        Collectible collectible = this.getActor().getBackpack().peek();


        //int y = getActor().getPosY() + getActor().getWidth() / 2 - collectible.getHeight() / 2;
        //int x = getActor().getPosX() + getActor().getWidth() / 2 - collectible.getWidth() / 2;

        int x =getActor().getPosX();
        int y =getActor().getPosY();

        try
        {
            this.getActor().getScene().addActor(collectible, x, y);

            this.getActor().getBackpack().remove(collectible);

        }
        catch (Exception ex)
        {
            this.getActor().getScene().getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);

        }

        if(collectible !=null)
        {

            this.getActor().getBackpack().remove(collectible);
//
//            getActor().getScene().addActor(collectible, x, y );

        }

    }

}
