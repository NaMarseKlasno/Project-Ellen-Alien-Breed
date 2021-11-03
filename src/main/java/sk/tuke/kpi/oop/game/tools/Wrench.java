package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Repairable;

public class Wrench extends BreakableTool<DefectiveLight> {

    private Animation wrench;

    public Wrench() {
        super(2);

        this.wrench = new Animation("sprites/wrench.png");
        setAnimation(this.wrench);
    }
//    public Hammer() {
//        this(1);
//    }

    @Override
    public void useWith(DefectiveLight LIGHT){
        if (LIGHT == null || super.getRemainingUses() < 1) return;

        super.useWith(LIGHT);
        LIGHT.repair();
    }
}
