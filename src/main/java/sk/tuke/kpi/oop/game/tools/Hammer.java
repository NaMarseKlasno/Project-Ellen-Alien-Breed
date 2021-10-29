package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Hammer extends AbstractActor
{
    private Animation hammer;
    private int number_of_uses;


    public Hammer ()
    {
        this.number_of_uses = 1;

        this.hammer = new Animation("sprites/hammer.png");
        setAnimation(hammer);
    }

    public void use() {
        --this.number_of_uses;
        if (this.number_of_uses == 0)
            this.getScene().removeActor(this);
    }

    public int get() {
        return this.number_of_uses;
    }
    public void push(int num) {
        this.number_of_uses = num;
    }

}
