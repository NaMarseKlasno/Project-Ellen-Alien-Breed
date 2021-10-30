package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;


public class FireExtinguisher extends BreakableTool {

    private Animation extinguisher;

    public FireExtinguisher() {
        super(1);

        this.extinguisher = new Animation("sprites/extinguisher.png");
        setAnimation(this.extinguisher);
    }
}

