package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm
{

    public Gun(int iammo, int maxammo)
    {

        super(iammo, maxammo);

    }
    ///////////////        ///////////////        ///////////////
    @Override
    protected Fireable createBullet()
    {

        return new Bullet();

    }

}
