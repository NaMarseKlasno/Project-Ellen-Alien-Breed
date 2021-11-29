package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

import java.util.Objects;

public class Fire <A extends Armed> extends AbstractAction<A>
{
    @Override
    public void execute(float deltaTime)
    {
        if (getActor() == null || isDone()) {
            setDone(true);
            return;
        } setDone(true);

        Fireable FIRE = getActor().getFirearm().fire();
        if (FIRE == null) return;


        Objects.requireNonNull(getActor().getScene()).addActor(FIRE,(getActor().getPosX()+8)+Direction.fromAngle(getActor().getAnimation().getRotation()).getDx()*24,(getActor().getPosY()+8)+Direction.fromAngle(getActor().getAnimation().getRotation()).getDy()*24);
        FIRE.startedMoving(Direction.fromAngle(getActor().getAnimation().getRotation()));
        new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()),Float.MAX_VALUE).scheduleFor(FIRE);
        System.out.println(Direction);
    }
}
