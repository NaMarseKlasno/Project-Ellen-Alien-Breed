package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;


public class Shift<A extends Keeper> extends AbstractAction<A>
{
    @Override
    public void execute(float deltaTime) {
        if (!isDone() & getActor() != null) {
            getActor().getBackpack().shift();
        } setDone(true);
//        if (Objects.requireNonNull(getActor()).getBackpack().peek() != null && getActor() != null && !isDone()) {
//            if (!isDone()) {
//                getActor().getBackpack().shift();
//                setDone(true);
//            }
//        } else setDone(true);
    }
}
