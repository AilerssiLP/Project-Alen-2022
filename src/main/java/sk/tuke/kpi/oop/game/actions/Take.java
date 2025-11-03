package sk.tuke.kpi.oop.game.actions;


//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
//import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;
import java.util.Objects;

public class Take<K extends Keeper> extends AbstractAction<K>
{//done
//    @Override
//    public void execute(float deltaTime) {
//         if(getActor().intersects(actor) && actor !=getActor()  ) {
//            getActor().getBackpack().add((Collectible) actor);
//            getActor().getScene().removeActor(actor);}});}

    public Take() {

    }

    @Override
    public void execute(float deltaTime)
    {


        if (getActor() == null || getActor().getScene() == null) {
            setDone(true);
            return;
        }
        //boolean backpackfull = false;
        if(isDone()==false){

            setDone(true);

            List<Actor> things = Objects.requireNonNull(getActor().getScene().getActors());

            try{things.forEach(actor ->
            {

                if(getActor().intersects(actor) && actor !=getActor()  )
                {

                    getActor().getBackpack().add((Collectible) actor);

                    getActor().getScene().removeActor(actor);

                }

            }

            );

            }catch(IllegalStateException ex){

                displayError(ex.getMessage());

            }

            catch (Exception ex)
            {

                displayError(ex.getMessage());

            }

        }

    }

    private void displayError(String message) {

        Scene scene = getActor().getScene();

        int x = 0;

        int y = 0;

        scene.getGame().getOverlay().drawText(message, x, y).showFor(2);
    }

}
