package sk.tuke.kpi.oop.game;

public enum Direction
{
    NORTH(0,1),
    SOUTH(0,-1),
    EAST(1,0),
    WEST(-1,0),
    NORTHEAST(1,1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    SOUTHWEST(-1,-1),
    NONE(0,0);;

    private int dx;
    private int dy;

    Direction(int dx,int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    private int dir;

    static
    {

        NORTH.dir =0;
        WEST.dir =90;
        SOUTH.dir =180;
        EAST.dir =270;

        NORTHWEST.dir =45;
        SOUTHWEST.dir =135;
        SOUTHEAST.dir =225;
        NORTHEAST.dir =315;

    }

    public static Direction fromAngle(float angle)
    {
        if (angle == 0)
        {
            return NORTH;

        }
        if (angle == 90)
        {

            return WEST;

        }
        if (angle == 180)
        {

            return SOUTH;

        }
        if (angle == 270)
        {

            return EAST;

        }
        if (angle == 45)
        {

            return NORTHWEST;

        }
        if (angle == 315)
        {

            return NORTHEAST;

        }
        if (angle == 135)
        {

            return SOUTHWEST;

        }
        if (angle == 225)
        {

            return SOUTHEAST;

        }

        return NONE;
    }
    ///////////////        ///////////////        ///////////////        ///////////////

    public float getAngle()
    {
//        //north
//        if(dx==0 && dy >0){
//            return 0;
//        }else if(dx==0 && dy < 0){//south
//            return 180;
//        }else if(dy==0 && dx < 0){//west
//            return 90;
//        }else if(dy==0 && dx > 0){//west
//            return 270;}
//
//        else if(dy>0 && dx > 0){//north west
//            return 315;
//        }else if(dy<0 && dx < 0){//south west
//            return 135;
//        }else if(dy<0 && dx > 0){//south east
//            return 225;
//        }else if(dy>0 && dx < 0){//north east
//            return 45;
//        }
//        //East
//        return 0;
//        //return dx > 0 && dy  == 0? 90 :0;

        return dir;
    }

    public int getDx()
    {
        return dx;
    }
    public int getDy()
    {
        return dy;
    }
    ///////////////        ///////////////        ///////////////        ///////////////
    public Direction combine(Direction other)
    {

        for (Direction dir :Direction.values())
        {

            int newDx;

            int newDy ;

            int get =dx+ other.dx;

            int gettwo=dy + other.dy;


            if(get<-1)
            {

                newDx =-1;

            }else if(get>1)
            {

                newDx =1;

            }else{

                newDx=get;

            }

            if(gettwo<-1)
            {

                newDy =-1;

            }else if(gettwo>1)
            {

                newDy =1;

            }else
            {

                newDy=gettwo;

            }

            if (dir.dx == newDx && dir.dy == newDy)
            {

                return dir;

            }

        }

        return NONE;
    }

}

//if(x==1 && y ==0) return -90;
//if(x==1 && y ==1) return 45;
//if(x==1 && y ==-1) return -90;
//
//if(x==0 && y ==0) return 0;
//if(x==0 && y ==1) return ;
//if(x==0 && y ==-1) return 180;
//
//if(x==-1 && y ==0) return 90;
//if(x==-1 && y ==1) return -315;
//if(x==-1 && y ==-1) return -225;
