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

    private List<Actor> ARR;


    public ChainBomb(float TIME)
    {
        super(TIME);
    }

    private void activate_nearby()
    {
        ARR = getScene().getActors();
        float X = (float)this.getPosX() - 50f, Y = (float)this.getPosY() - 50f;
        Ellipse2D.Float ChainBomb_ellipse = new Ellipse2D.Float(X, Y, 100f, 100f);

        for (Actor ACTOR : ARR)
        {
            if (ACTOR instanceof ChainBomb)
            {
                X = ACTOR.getPosX() - (float)ACTOR.getWidth() / 2;
                Y = ACTOR.getPosY() - (float)ACTOR.getHeight() / 2;

                Rectangle2D other_bomb = new Rectangle2D.Float(X, Y, ACTOR.getWidth(),ACTOR.getHeight());

                if (ChainBomb_ellipse.contains(other_bomb) || ChainBomb_ellipse.intersects(other_bomb))
                    ((ChainBomb)ACTOR).activate();
            }
        }
    }

    @Override
    public void activate()
    {
        if (isActivated()) return;
        new ActionSequence<>(
            new Invoke<>(this::activateBomb),
            new Wait<>(this.getTIME()),
            new Invoke<>(this::activate_nearby),
            new Invoke<>(this::explode),
            new Wait<>(1f),
            new Invoke<>(this::remove)
        ).scheduleFor(this);
    }

}
