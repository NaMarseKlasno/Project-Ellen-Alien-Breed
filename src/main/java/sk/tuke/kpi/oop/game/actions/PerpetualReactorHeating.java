package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;


public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int variable;

    public PerpetualReactorHeating(int action) {
        super();
        this.variable = action;
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null || !getActor().isOn()) return;

        getActor().increaseTemperature(this.variable);
    }
}
