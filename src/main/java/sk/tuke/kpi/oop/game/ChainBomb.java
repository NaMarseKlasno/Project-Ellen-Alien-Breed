package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import java.awt.geom.Ellipse2D;
import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;
import java.util.List;
//import java.util.Set;

public class ChainBomb extends TimeBomb {

    public ChainBomb(float TIME)
    {
        super(TIME);
    }

    private void activatenearby()
    {
        List<Actor> ARR = getScene().getActors();

        int X = this.getPosX() - 42;
        int Y = this.getPosY() - 58;
        Ellipse2D.Float ChainBomb_ellipse = new Ellipse2D.Float(X, Y, 100f, 100f);

        for (Actor ACTOR : ARR)
        {
            if (ACTOR instanceof ChainBomb)
            {
                X = ACTOR.getPosX() - ACTOR.getWidth() / 2;
                Y = ACTOR.getPosY() - ACTOR.getHeight() / 2;

                Rectangle2D other_bomb = new Rectangle2D.Float(X, Y, ACTOR.getWidth(),ACTOR.getHeight());

                if (ChainBomb_ellipse.contains(other_bomb) || ChainBomb_ellipse.intersects(other_bomb))
                    ((ChainBomb)ACTOR).activate();
            }
        }
    }

    @Override
    public void activate()
    {
        //System.out.println(super.isActivated());
        if (isActivated()) return;
        new ActionSequence<>(
            new Invoke<>(this::setTrueStatus),
            new Invoke<>(this::activateBomb),
            new Wait<>(this.getTIME()),
            new Invoke<>(this::activatenearby),
            new Invoke<>(this::explode),
            new Wait<>(1f),
            new Invoke<>(this::remove)
        ).scheduleFor(this);
    }
}
