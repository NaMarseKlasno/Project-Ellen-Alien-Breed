package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;
import java.util.List;
import java.util.Objects;


public class Take<A extends Keeper> extends AbstractAction<A>
{
    private Keeper KEEPER;

    public Take(Keeper keeper) {
        this.KEEPER = keeper;
    }

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() == null || getActor().getScene() == null) {
            setDone(true);
            return;
        } Scene scene = this.KEEPER.getScene();

        if (isDone()) return;

        List<Actor> LIST = Objects.requireNonNull(this.KEEPER.getScene()).getActors();

        for (Actor tool : LIST)
        {
            if (!(tool instanceof Collectible) || !(tool.intersects(this.KEEPER))) continue;

            try {
                this.KEEPER.getBackpack().add(((Collectible) tool));
                assert scene != null;
                scene.removeActor(tool);
                break;
            } catch (Exception ex) {
                assert scene != null;
                scene.getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
            }
        }
        setDone(true);
    }
}
