package sk.tuke.kpi.oop.game.openables;

//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.map.SceneMap;
//import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.gamelib.graphics.Animation;
//import sk.tuke.kpi.gamelib.graphics.Point;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.ArrayList;
import java.util.List;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    private boolean open = false;

    //private Actor actor;
    private MapTile.Type type;

    private List<MapTile> tiles = null;

    private boolean way;

    //private Orientation orientation;


    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);


    public Door(String name, Orientation orientation){
        super(name);
        ///////////////

        Animation.PlayMode playMode ;


        if (open)
        {

            playMode = Animation.PlayMode.ONCE;

        }
        else{

            playMode = Animation.PlayMode.ONCE_REVERSED;

        }

        if(orientation == Orientation.VERTICAL)
        {

            setAnimation(new Animation("sprites/vdoor.png", 16,32, 0.1f, playMode));

            way =true;

        }else
        {

            setAnimation(new Animation("sprites/hdoor.png", 32,16, 0.1f, playMode));

            way=false;

        }
        ///////////////

    }

    @Override
    public void open(){

        if (open==false)
        {

//            if(way){
//                setAnimation(new Animation("sprites/hdoor.png",16,32,0.1f,
//                    Animation.PlayMode.ONCE));
//            }else{
//                setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f,
//                    Animation.PlayMode.ONCE));
//            }

            if(way)
            {

                getAnimation().setPlayMode(Animation.PlayMode.ONCE);
                ///////////////
            }

            getAnimation().setPlayMode(Animation.PlayMode.ONCE);

            ///////////////        ///////////////        ///////////////

            getScene().getMessageBus().publish(DOOR_OPENED, this);

            open = true;
            ///////////////        ///////////////        ///////////////

            if(isOpen())
            {

                type =MapTile.Type.CLEAR;

            }else
            {

                type =MapTile.Type.WALL;

            }


            for (MapTile tile: findTiles())
            {

                tile.setType(type);

            }

        }
        //getScene().getMessageBus().publish(DOOR);
    }

    @Override
    public void close()
    {

        if (open==true)
        {
//            if(way){
//                setAnimation(new Animation("sprites/hdoor.png",16,32,0.1f,
//                    Animation.PlayMode.ONCE_REVERSED));
//            }else{
//                setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f,
//                    Animation.PlayMode.ONCE_REVERSED));
//            }

            if(way)
            {
                ///////////////        ///////////////        ///////////////
                getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);

            }

            getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);

            ///////////////        ///////////////        ///////////////


            getScene().getMessageBus().publish(DOOR_CLOSED, this);

            open = false;

            if(isOpen())
            {

                type =MapTile.Type.CLEAR;

            }else
            {

                type =MapTile.Type.WALL;

            }

            for (MapTile tile: findTiles())
            {

                tile.setType(type);

            }

        }
        ///////////////        ///////////////        ///////////////        ///////////////
    }

    @Override
    public boolean isOpen()
    {

        return open;

    }

    @Override
    public void useWith(Actor actor)
    {
//        SceneMap sceneMap = actor.getScene().getMap();
//        sceneMap.getTile(5,5).setType(MapTile.Type.WALL);
//        sceneMap.getTile(6,6).setType(MapTile.Type.WALL);
//        close();
        if(isOpen())
        {

            close();

        }
        else{

            open();

        }

    }

    @Override
    public Class<Actor> getUsingActorClass()
    {
        return Actor.class;
    }


    ///////////////////////////////////// repair
    private List<MapTile> findTiles()
    {
        int divide;

        int divides;


        List<MapTile> tilesfound = new ArrayList<>();

        SceneMap map = getScene().getMap();


        if (tiles != null)
        {

            return tiles;

        }

        divide = getAnimation().getWidth() / map.getTileWidth();

        divides = getAnimation().getHeight() / map.getTileHeight();

        for (int i =0; i <divide; i++) {

            for (int j =0; j <divides; j++) {

                MapTile tile = map.getTile(

                    getPosX()/map.getTileWidth() + i,
                    getPosY()/map.getTileHeight() + j

                );
                ///////////////        ///////////////        ///////////////
                tilesfound.add(tile);

            }
            ///////////////        ///////////////        ///////////////

        }

        tiles = tilesfound;

        return tiles;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {

        super.addedToScene(scene);

        if(isOpen())
        {

            type =MapTile.Type.CLEAR;

        }else
        {

            type =MapTile.Type.WALL;

        }


        for (MapTile tile: findTiles())
        {

            tile.setType(type);

        }
        ////////////////
        ////////////////
        ////////////////
    }


}
