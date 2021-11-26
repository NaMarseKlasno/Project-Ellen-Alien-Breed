package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Energy extends AbstractActor implements Usable<Alive> {

    public Energy()
    {
        setAnimation(new Animation("sprites/energy.png"));
    }

    @Override
    public void useWith(Alive actor) {
        if (actor == null || actor.getHealth().getValue() == 100) return;

        actor.getHealth().refill(100);
        (Objects.requireNonNull(getScene())).removeActor(this);
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
