package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;


public class Drop <A extends Keeper> extends AbstractAction<A>
{
    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null || isDone()) return;

        @Nullable Collectible ITEM = Objects.requireNonNull(this.getActor()).getBackpack().peek();
        if (ITEM == null) return;

        Objects.requireNonNull(getActor().getScene()).addActor(ITEM, (this.getActor().getPosX() + (this.getActor().getWidth() - ITEM.getWidth()/2)), (this.getActor().getPosY() + (this.getActor().getHeight() - ITEM.getHeight()/2)));
        this.getActor().getBackpack().remove(ITEM);
        setDone(true);
    }
}
