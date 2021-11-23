
package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop <A extends Keeper> extends AbstractAction<A> {
    private Keeper keeper;

    public Drop(Keeper keeper) {
        this.keeper = keeper;
    }


    @Override
    public void execute(float deltaTime)
    {
        Scene scene = keeper.getScene();
        if (scene == null) return;

        @Nullable Collectible ITEM = keeper.getBackpack().peek();
        if (ITEM == null) return;

        scene.addActor(ITEM, (keeper.getPosX() + (keeper.getWidth() - ITEM.getWidth()/2)), (keeper.getPosY() + (keeper.getHeight() - ITEM.getHeight()/2)));
        keeper.getBackpack().remove(ITEM);
        setDone(true);
    }
}
