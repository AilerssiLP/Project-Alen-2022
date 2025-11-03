package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.Nullable;

public abstract class Firearm
{
    private int ammo;

    private final int maxammo;

    //boolean remainingammo;

    public Firearm(int Startammo)
    {

        this.ammo = Startammo;

        this.maxammo = Startammo;

    }

    public Firearm(int Startammo, int Imaxammo)
    {

        this.ammo = Startammo;

        this.maxammo = Imaxammo;

    }



    public void reload(int newAmmo)
    {
        if(ammo <maxammo-newAmmo)
        {

            ammo=ammo+newAmmo;

        }else
        {

            ammo =maxammo;

        }

    }
    ///////////////        ///////////////        ///////////////
    public int getAmmoMax()
    {

        return maxammo;

    }

    public int getAmmo()
    {

        return ammo;

    }

    protected abstract Fireable createBullet();


    ///////////////        ///////////////        ///////////////        ///////////////
    @Nullable
    public Fireable fire()
    {

        if (ammo >0)
        {

            ammo =ammo-1;

            if(ammo>0)
            {

                return createBullet();

            }

        }

        return null;
    }


}
