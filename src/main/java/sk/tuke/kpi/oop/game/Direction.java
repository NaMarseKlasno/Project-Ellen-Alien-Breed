package sk.tuke.kpi.oop.game;

import static java.lang.StrictMath.atan2;
import static java.lang.Math.toDegrees;

public enum Direction
{
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NONE(0, 0),
    NORTHEAST(1,1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    SOUTHWEST(-1,-1);

    private final int dx;
    private final int dy;


    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public float getAngle() {
        float ANGLE = (float)toDegrees(atan2(this.dx, this.dy));
        if (ANGLE != 0) return -ANGLE;
        return ANGLE;
    }

    public Direction combine(Direction DIR)
    {
        if (DIR == null) return NONE;

        int DX = this.getDx() + DIR.getDx(), DY = this.getDy() + DIR.getDy();

        if (DX > 1) DX = 1;
        else if (DX < -1) DX = -1;
        if (DY > 1) DY = 1;
        else if (DY < -1) DY = -1;

        for (Direction val: Direction.values())
        {
            if (val.getDx() == DX && val.getDy() == DY) return val;
        }
        return NONE;
    }
}
