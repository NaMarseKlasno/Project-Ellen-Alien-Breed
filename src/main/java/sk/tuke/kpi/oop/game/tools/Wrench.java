package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight>{

    //private int health = 2;


    public Wrench(){
        super(2);
        Animation wrenchAnimation = new Animation("sprites/wrench.png");
        //setRemainingUses(health);
        setAnimation(wrenchAnimation);
    }
    @Override
    public void useWith(DefectiveLight actor) {
        if(actor != null && this.getRemainingUses() > 0 && !actor.isX() && actor.isPowered()) {
            actor.repair();
            super.useWith(actor);
        }
    }
}
