package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;

//import static java.util.Arrays.copyOf;
//import java.util.List;

//import static java.util.List.copyOf;

public class Backpack implements ActorContainer<Collectible>
{

    private int capacity;
    private String name;
    private List<Collectible> bagies;
    private int size;

    public Backpack(String name, int capacity)
    {
        this.capacity =capacity;
        ////////////////
        this.name =name;
        ////////////////
        this.bagies =new ArrayList<>();
        ////////////////
        //this.capacity =10;
        //this.name = "Bagieee";
    }


    @Override
    public @NotNull List<Collectible> getContent()
    {
        ////////////////
        return List.copyOf(bagies) ;// treba vymenit copyof

    }

    @Override
    public @NotNull String getName()
    {
        ////////////////
        return name;

    }

    @Override
    public int getSize()
    {
        return size;
        ////////////////
    }

    @Override
    public int getCapacity()
    {
        ////////////////
        return capacity;

    }

    @Override
    public void add(@NotNull Collectible collectible)
    {
        int sizek = this.size + 1;
        ////////////////
        ////////////////

        if(sizek <this.capacity)
        {

            bagies.add(collectible);

            size =size + 1;
            //bagies.add(collectible);
        }else
            throw new IllegalStateException(name + " is full");//not sure
////////////////

    }

    @Override
    public void remove(@NotNull Collectible collectible)
    {

        bagies.remove(collectible);

    }

    @Override
    public void shift()
    {

        if(size == 0 || size == 1)
        {

            return;

        }else

            bagies.add(0, bagies.get(size - 1));

        bagies.remove(size);

    }

    @Override
    public @Nullable Collectible peek()
    {

        if(size >0)
        {

            return bagies.get(bagies.size()-1);
        }else
        {

            return null;

        }


    }



    @NotNull
    @Override
    public Iterator<Collectible> iterator()
    {

        return bagies.iterator();

    }

}
