package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;


public class Drop <A extends Keeper> extends AbstractAction<A>
{
    private Keeper KEEPERR;

    public Drop(Keeper keeper) {
        this.KEEPERR = keeper;
    }


    @Override
    public void execute(float deltaTime)
    {
        Scene scene = KEEPERR.getScene();
        if (scene == null) return;

        @Nullable Collectible ITEM = KEEPERR.getBackpack().peek();
        if (ITEM == null) return;

        scene.addActor(ITEM, (KEEPERR.getPosX() + (KEEPERR.getWidth() - ITEM.getWidth()/2)), (KEEPERR.getPosY() + (KEEPERR.getHeight() - ITEM.getHeight()/2)));
        KEEPERR.getBackpack().remove(ITEM);
        setDone(true);
    }
}
