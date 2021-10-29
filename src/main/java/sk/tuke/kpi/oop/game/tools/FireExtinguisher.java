package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {

    private Animation no_fire;
    private int number_of_uses;


    public FireExtinguisher() {
        this.number_of_uses = 1;

        this.no_fire = new Animation("sprites/extinguisher.png");
        setAnimation(no_fire);
    }

    public void use() {
        --this.number_of_uses;
        if (this.number_of_uses == 0)
            this.getScene().removeActor(this);
    }

    public int get() {
        return this.number_of_uses;
    }

}
