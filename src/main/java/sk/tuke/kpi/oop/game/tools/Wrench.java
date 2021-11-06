package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
//import sk.tuke.kpi.oop.game.Repairable;

public class Wrench extends BreakableTool<DefectiveLight> {

    //private Animation wrench;
    private boolean isPochinena;

    public Wrench() {
        super(2);
        this.isPochinena = false;

        //this.wrench = new Animation("sprites/wrench.png");
        setAnimation(new Animation("sprites/wrench.png"));
    }
//    public Hammer() {
//        this(1);
//    }

    @Override
    public void useWith(DefectiveLight LIGHT){
        if (LIGHT == null || super.getRemainingUses() < 1 || this.isPochinena) return;
        // repairable!=null && repairable.repair()
        this.isPochinena = true;
        super.useWith(LIGHT);
        LIGHT.repair();
    }
}
