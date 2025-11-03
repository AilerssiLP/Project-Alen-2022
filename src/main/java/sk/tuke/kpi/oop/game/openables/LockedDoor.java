package sk.tuke.kpi.oop.game.openables;

//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.oop.game.items.Usable;

public class LockedDoor extends Door
{
    //private boolean unlock =false;
    private boolean locked = true;

    public LockedDoor(String name, Orientation orientation)
    {

        super(name, orientation);

    }


    public void unlock()
    {
        locked =true;

        if (isLocked()) {
            ///////////////

            open();

        }
    }
    public void lock()
    {

        locked =false;

        if (!isLocked())
        {

            close();

        }

    }
    ///////////////        ///////////////        ///////////////        ///////////////

    public void setLocked(boolean locked)
    {

        this.locked = locked;

    }

    public boolean isLocked()
    {

        return locked;

    }

}
