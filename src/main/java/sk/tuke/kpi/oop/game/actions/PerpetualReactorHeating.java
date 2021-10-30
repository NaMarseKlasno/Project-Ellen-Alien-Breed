package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;


public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int variable;
//    private Reactor actor;
//    private boolean isDone;

    public PerpetualReactorHeating(int action) {
        super();

        this.variable = action;
//        this.actor = getActor();
//        this.isDone = isDone();
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null) return;

        getActor().increaseTemperature(this.variable);
    }
}
