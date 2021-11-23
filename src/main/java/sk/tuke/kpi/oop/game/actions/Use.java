package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.gamelib.Actor;

public class Use<A extends Actor> extends AbstractAction<A> {

    private Usable<A> ITEM;

    public Use(Usable<A> item) {
        this.ITEM = item;
    }


    @Override
    public void execute(float deltaTime) {
        if (isDone()) return;
        ITEM.useWith(getActor());
        setDone(true);
    }
}
