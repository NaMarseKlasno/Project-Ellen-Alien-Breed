package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Hammer extends AbstractActor
{
    private Animation hammer;
    private int count;

    public Hammer ()
    {
        ++this.count;

        this.hammer = new Animation("sprites/hammer.png");
        setAnimation(hammer);
    }

    public void use() {
        --this.count;
        if (this.count == 0)
            this.getScene().removeActor(this);
    }

}
