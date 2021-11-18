package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Ripley> {

    public Ammo() {
        setAnimation(new Animation("sprites/ammo.png"));
    }


    @Override
    public void useWith(Ripley actor) {
        if (actor == null || actor.getAMMO() == 500) return;

        if (actor.getAMMO() <= 450) actor.setAMMO(actor.getAMMO() + 50);
        else actor.setAMMO(500);

        Objects.requireNonNull(getScene()).removeActor(this);
    }
}
